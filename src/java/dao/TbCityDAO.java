/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.TbCity;
import util.PersistenceUtil;

/**
 *
 * @author lucas.vianna
 */
public class TbCityDAO {
    
    public static TbCityDAO tbCityDAO;

    public static TbCityDAO getInstance() {
        if (tbCityDAO == null) {
            tbCityDAO = new TbCityDAO();
        }
        return tbCityDAO;
    }

    public TbCity buscar(int idCity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select c from TbCity As c where c.idCity =:idCity ");
        query.setParameter("idCity", idCity);

        List<TbCity> cities = query.getResultList();
        if (cities != null && cities.size() > 0) {
            return cities.get(0);
        }

        return null;
    }

    public List<TbCity> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from idCity As c");
        return query.getResultList();
    }

    public void remover(TbCity tbCity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(tbCity);
        em.getTransaction().commit();
    }

    public TbCity persistir(TbCity tbCity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            tbCity = em.merge(tbCity);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbCity;
    }
}
