/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.TbSubfunctions;
import util.PersistenceUtil;

/**
 *
 * @author amand
 */
public class TbSubfunctionsDAO {
            public static TbSubfunctionsDAO tbSubfunctionsDAO;

    public static TbSubfunctionsDAO getInstance() {
        if (tbSubfunctionsDAO == null) {
            tbSubfunctionsDAO = new TbSubfunctionsDAO();
        }
        return tbSubfunctionsDAO;
    }

    public TbSubfunctions buscar(int idSubfunction) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select su from TbSubfunctions As su where su.idSubfunction =:idSubfunction ");
        query.setParameter("idSubfunction", idSubfunction);

        List<TbSubfunctions> subfunctions = query.getResultList();
        if (subfunctions != null && subfunctions.size() > 0) {
            return subfunctions.get(0);
        }

        return null;
    }

    public List<TbSubfunctions> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from idSubfunction As su");
        return query.getResultList();
    }

    public void remover(TbSubfunctions tbSubfunctions) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(tbSubfunctions);
        em.getTransaction().commit();
    }

    public TbSubfunctions persistir(TbSubfunctions tbSubfunctions) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            tbSubfunctions = em.merge(tbSubfunctions);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbSubfunctions;
    }  
}
