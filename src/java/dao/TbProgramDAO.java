/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.TbProgram;
import util.PersistenceUtil;

/**
 *
 * @author amand
 */
public class TbProgramDAO {
          public static TbProgramDAO tbProgramDAO;

    public static TbProgramDAO getInstance() {
        if (tbProgramDAO == null) {
            tbProgramDAO = new TbProgramDAO();
        }
        return tbProgramDAO;
    }

    public TbProgram buscar(int idProgram) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select pr from TbProgram As pr where pr.idProgram =:idProgram ");
        query.setParameter("idProgram", idProgram);

        List<TbProgram> programs = query.getResultList();
        if (programs != null && programs.size() > 0) {
            return programs.get(0);
        }

        return null;
    }

    public List<TbProgram> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from idProgram As pr");
        return query.getResultList();
    }

    public void remover(TbProgram tbProgram) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(tbProgram);
        em.getTransaction().commit();
    }

    public TbProgram persistir(TbProgram tbProgram) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            tbProgram = em.merge(tbProgram);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbProgram;
    }
}
