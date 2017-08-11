/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Payment;
import util.PersistenceUtil;

/**
 *
 * @author lucas.vianna
 */
public class PaymentDAO {

 public static PaymentDAO paymentDAO;

    public static PaymentDAO getInstance() {
        if (paymentDAO == null) {
            paymentDAO = new PaymentDAO();
        }
        return paymentDAO;
    }

    public Payment buscar(int idPayment) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select p from Payment As p where p.idPayment =:idPayment ");
        query.setParameter("idPayment", idPayment);

        List<Payment> payments = query.getResultList();
        if (payments != null && payments.size() > 0) {
            return payments.get(0);
        }
        return null;
    }

    public List<Payment> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Payment As p");
        return query.getResultList();
    }

    public void remover(Payment payment) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(payment);
        em.getTransaction().commit();
    }

    public Payment persistir(Payment payment) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            payment = em.merge(payment);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }    
}
