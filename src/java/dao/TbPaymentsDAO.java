/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.TbPayments;
import util.PersistenceUtil;

/**
 *
 * @author amand
 */
public class TbPaymentsDAO {
    public static TbPaymentsDAO tbPaymentsDAO;

    public static TbPaymentsDAO getInstance() {
        if (tbPaymentsDAO == null) {
            tbPaymentsDAO = new TbPaymentsDAO();
        }
        return tbPaymentsDAO;
    }

    public TbPayments buscar(int idPayment) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select p from TbPayments As p where p.idPayment =:idPayment ");
        query.setParameter("idPayment", idPayment);

        List<TbPayments> payments = query.getResultList();
        if (payments != null && payments.size() > 0) {
            return payments.get(0);
        }

        return null;
    }

    public List<TbPayments> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from idPayment As p");
        return query.getResultList();
    }

    public void remover(TbPayments tbPayments) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(tbPayments);
        em.getTransaction().commit();
    }

    public TbPayments persistir(TbPayments tbPayments) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            tbPayments = em.merge(tbPayments);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbPayments;
    } 
}
