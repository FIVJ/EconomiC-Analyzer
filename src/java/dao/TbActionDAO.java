/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.TbAction;
import util.PersistenceUtil;

/**
 *
 * @author lucas.vianna
 */
public class TbActionDAO {
    
    public static TbActionDAO tbActionDAO;

    public static TbActionDAO getInstance() {
        if (tbActionDAO == null) {
            tbActionDAO = new TbActionDAO();
        }
        return tbActionDAO;
    }

    public TbAction buscar(int idAction) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from TbAction As a where a.idAction =:idAction ");
        query.setParameter("idAction", idAction);

        List<TbAction> action = query.getResultList();
        if (action != null && action.size() > 0) {
            return action.get(0);
        }

        return null;
    }

    public List<TbAction> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from TbAction As a");
        return query.getResultList();
    }

    public void remover(TbAction tbAction) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(tbAction);
        em.getTransaction().commit();
    }

    public TbAction persistir(TbAction tbAction) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            tbAction = em.merge(tbAction);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbAction;
    }

}
