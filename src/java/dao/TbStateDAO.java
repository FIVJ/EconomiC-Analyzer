/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.TbState;
import util.PersistenceUtil;

/**
 *
 * @author amand
 */
public class TbStateDAO {
         public static TbStateDAO tbStateDAO;

    public static TbStateDAO getInstance() {
        if (tbStateDAO == null) {
            tbStateDAO = new TbStateDAO();
        }
        return tbStateDAO;
    }

    public TbState buscar(int idState) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select st from TbState As st where st.idState =:idState ");
        query.setParameter("idState", idState);

        List<TbState> states = query.getResultList();
        if (states != null && states.size() > 0) {
            return states.get(0);
        }

        return null;
    }

    public List<TbState> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from idState As st");
        return query.getResultList();
    }

    public void remover(TbState tbState) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(tbState);
        em.getTransaction().commit();
    }

    public TbState persistir(TbState tbState) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            tbState = em.merge(tbState);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbState;
    } 
}
