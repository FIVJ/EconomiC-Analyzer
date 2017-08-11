/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Region;
import util.PersistenceUtil;

/**
 *
 * @author lucas.vianna
 */
public class RegionDAO {
    
 public static RegionDAO regionDAO;

    public static RegionDAO getInstance() {
        if (regionDAO == null) {
            regionDAO = new RegionDAO();
        }
        return regionDAO;
    }

    public Region buscar(int idRegion) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select r from Region As r where r.idRegion =:idRegion ");
        query.setParameter("idRegion", idRegion);

        List<Region> regions = query.getResultList();
        if (regions != null && regions.size() > 0) {
            return regions.get(0);
        }
        return null;
    }

    public List<Region> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Region As r");
        return query.getResultList();
    }

    public void remover(Region region) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(region);
        em.getTransaction().commit();
    }

    public Region persistir(Region region) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            region = em.merge(region);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return region;
    }      
        
    
}
