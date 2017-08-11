/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Favored;
import util.PersistenceUtil;

/**
 *
 * @author lucas.vianna
 */
public class FavoredDAO {
    
    public static FavoredDAO favoredDAO;

    public static FavoredDAO getInstance() {
        if (favoredDAO == null) {
            favoredDAO = new FavoredDAO();
        }
        return favoredDAO;
    }

    public Favored buscar(int idFavored) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select f from Favored As f where f.idFavored =:idFavored ");
        query.setParameter("idFavored", idFavored);

        List<Favored> favored = query.getResultList();
        if (favored != null && favored.size() > 0) {
            return favored.get(0);
        }
        return null;
    }

    public List<Favored> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Favored As f");
        return query.getResultList();
    }

    public void remover(Favored favored) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(favored);
        em.getTransaction().commit();
    }

    public Favored persistir(Favored favored) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            favored = em.merge(favored);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return favored;
    }
    
}
