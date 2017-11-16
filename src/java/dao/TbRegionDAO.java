/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.TbRegion;
import util.PersistenceUtil;

/**
 *
 * @author amand
 */
public class TbRegionDAO {
    public static TbRegionDAO tbRegionDAO;

    public static TbRegionDAO getInstance() {
        if (tbRegionDAO == null) {
            tbRegionDAO = new TbRegionDAO();
        }
        return tbRegionDAO;
    }

    public TbRegion buscar(int idRegion) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select r from TbRegion As r where r.idRegion =:idRegion ");
        query.setParameter("idRegion", idRegion);

        List<TbRegion> regions = query.getResultList();
        if (regions != null && regions.size() > 0) {
            return regions.get(0);
        }

        return null;
    }

    public List<TbRegion> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from idRegion As r");
        return query.getResultList();
    }

    public void remover(TbRegion tbRegion) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(tbRegion);
        em.getTransaction().commit();
    }

    public TbRegion persistir(TbRegion tbRegion) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            tbRegion = em.merge(tbRegion);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbRegion;
    } 
}
