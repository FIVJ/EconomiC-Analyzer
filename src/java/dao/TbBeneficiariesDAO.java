/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.TbBeneficiaries;
import util.PersistenceUtil;

/**
 *
 * @author lucas.vianna
 */
public class TbBeneficiariesDAO {
    
    public static TbBeneficiariesDAO tbBeneficiariesDAO;

    public static TbBeneficiariesDAO getInstance() {
        if (tbBeneficiariesDAO == null) {
            tbBeneficiariesDAO = new TbBeneficiariesDAO();
        }
        return tbBeneficiariesDAO;
    }

    public TbBeneficiaries buscar(int idBeneficiaries) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select b from TbBeneficiaries As b where b.idBeneficiaries =:idBeneficiaries ");
        query.setParameter("idBeneficiaries", idBeneficiaries);

        List<TbBeneficiaries> beneficiaries = query.getResultList();
        if (beneficiaries != null && beneficiaries.size() > 0) {
            return beneficiaries.get(0);
        }

        return null;
    }

    public List<TbBeneficiaries> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from TbBeneficiaries As b");
        return query.getResultList();
    }

    public void remover(TbBeneficiaries tbBeneficiaries) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(tbBeneficiaries);
        em.getTransaction().commit();
    }

    public TbBeneficiaries persistir(TbBeneficiaries tbBeneficiaries) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            tbBeneficiaries = em.merge(tbBeneficiaries);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbBeneficiaries;
    }
}
