/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.TbSource;
import util.PersistenceUtil;

/**
 *
 * @author amand
 */
public class TbSourceDAO {
    
    public static TbSourceDAO tbSourceDAO;

    public static TbSourceDAO getInstance() {
        if (tbSourceDAO == null) {
            tbSourceDAO = new TbSourceDAO();
        }
        return tbSourceDAO;
    }

    public TbSource buscar(int idSource) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select s from TbSource As s where s.idSource =:idSource ");
        query.setParameter("idSource", idSource);

        List<TbSource> sources = query.getResultList();
        if (sources != null && sources.size() > 0) {
            return sources.get(0);
        }

        return null;
    }

    public List<TbSource> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from idSource As s");
        return query.getResultList();
    }

    public void remover(TbSource tbSource) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(tbSource);
        em.getTransaction().commit();
    }

    public TbSource persistir(TbSource tbSource) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            tbSource = em.merge(tbSource);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbSource;
    } 
}
