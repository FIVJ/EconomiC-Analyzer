/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Fisherman;
import util.PersistenceUtil;

/**
 *
 * @author lucas.vianna
 */
public class FishermanDAO {

    public static FishermanDAO fishermanDAO;

    public static FishermanDAO getInstance() {
        if (fishermanDAO == null) {
            fishermanDAO = new FishermanDAO();
        }
        return fishermanDAO;
    }

    public Fisherman buscar(int idFisherman) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select fi from Fisherman As fi where fi.idFisherman =:idFisherman ");
        query.setParameter("idFisherman", idFisherman);

        List<Fisherman> fisherman = query.getResultList();
        if (fisherman != null && fisherman.size() > 0) {
            return fisherman.get(0);
        }
        return null;
    }

    public List<Fisherman> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Fisherman As fi");
        return query.getResultList();
    }

    public void remover(Fisherman fisherman) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(fisherman);
        em.getTransaction().commit();
    }

    public Fisherman persistir(Fisherman fisherman) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            fisherman = em.merge(fisherman);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fisherman;
    }
        
}
