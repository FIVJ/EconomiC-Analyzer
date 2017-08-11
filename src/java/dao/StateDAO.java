/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.State;
import util.PersistenceUtil;

/**
 *
 * @author lucas.vianna
 */
public class StateDAO {
 
 public static StateDAO stateDAO;

    public static StateDAO getInstance() {
        if (stateDAO == null) {
            stateDAO = new StateDAO();
        }
        return stateDAO;
    }

    public State buscar(int idState) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select s from State As s where s.idState =:idState ");
        query.setParameter("idState", idState);

        List<State> states = query.getResultList();
        if (states != null && states.size() > 0) {
            return states.get(0);
        }
        return null;
    }

    public List<State> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from State As s");
        return query.getResultList();
    }

    public void remover(State state) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(state);
        em.getTransaction().commit();
    }

    public State persistir(State state) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            state = em.merge(state);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }      
        
        
}
