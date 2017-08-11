/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.City;
import util.PersistenceUtil;

/**
 *
 * @author lucas.vianna
 */
public class CityDAO {
    
    public static CityDAO cityDAO;

    public static CityDAO getInstance() {
        if (cityDAO == null) {
            cityDAO = new CityDAO();
        }
        return cityDAO;
    }

    public City buscar(int idCity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select c from City As c where c.idCity =:idCity ");
        query.setParameter("idCity", idCity);

        List<City> Cities = query.getResultList();
        if (Cities != null && Cities.size() > 0) {
            return Cities.get(0);
        }

        return null;
    }

    public List<City> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from City As c");
        return query.getResultList();
    }

    public void remover(City city) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(city);
        em.getTransaction().commit();
    }

    public City persistir(City city) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            city = em.merge(city);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }
    
}
