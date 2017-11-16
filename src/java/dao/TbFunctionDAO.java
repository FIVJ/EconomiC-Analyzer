/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.TbFunctions;
import util.PersistenceUtil;

/**
 *
 * @author amand
 */
public class TbFunctionDAO {
   
          public static TbFunctionDAO tbFunctionDAO;

    public static TbFunctionDAO getInstance() {
        if (tbFunctionDAO == null) {
            tbFunctionDAO = new TbFunctionDAO();
        }
        return tbFunctionDAO;
    }

    public TbFunctions buscar(int idFunction) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select fu from TbFunctions As fu where fu.idFunction =:idFuntion ");
        query.setParameter("idFunction", idFunction);

        List<TbFunctions> function = query.getResultList();
        if (function != null && function.size() > 0) {
            return function.get(0);
        }

        return null;
    }

    public List<TbFunctions> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from idFunction As fu");
        return query.getResultList();
    }

    public void remover(TbFunctions tbFunctions) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(tbFunctions);
        em.getTransaction().commit();
    }

    public TbFunctions persistir(TbFunctions tbFunctions) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            tbFunctions = em.merge(tbFunctions);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbFunctions;
    }
}
