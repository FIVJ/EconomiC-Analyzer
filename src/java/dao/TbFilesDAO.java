/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.TbFiles;
import util.PersistenceUtil;

/**
 *
 * @author lucas.vianna
 */
public class TbFilesDAO {
    
    public static TbFilesDAO tbFilesDAO;

    public static TbFilesDAO getInstance() {
        if (tbFilesDAO == null) {
            tbFilesDAO = new TbFilesDAO();
        }
        return tbFilesDAO;
    }

    public TbFiles buscar(int idFile) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select f from TbFiles As f where f.idFile =:idFile ");
        query.setParameter("idFile", idFile);

        List<TbFiles> files = query.getResultList();
        if (files != null && files.size() > 0) {
            return files.get(0);
        }

        return null;
    }

    public List<TbFiles> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from idFile As f");
        return query.getResultList();
    }

    public void remover(TbFiles tbFiles) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(tbFiles);
        em.getTransaction().commit();
    }

    public TbFiles persistir(TbFiles tbFiles) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            tbFiles = em.merge(tbFiles);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbFiles;
    }
}
