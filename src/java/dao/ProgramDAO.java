/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Program;
import util.PersistenceUtil;

/**
 *
 * @author lucas.vianna
 */
public class ProgramDAO {
    
 public static ProgramDAO programDAO;

    public static ProgramDAO getInstance() {
        if (programDAO == null) {
            programDAO = new ProgramDAO();
        }
        return programDAO;
    }

    public Program buscar(int idProgram) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select p from Program As p where p.idProgram =:idProgram ");
        query.setParameter("idProgram", idProgram);

        List<Program> programs = query.getResultList();
        if (programs != null && programs.size() > 0) {
            return programs.get(0);
        }
        return null;
    }

    public List<Program> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Program As p");
        return query.getResultList();
    }

    public void remover(Program program) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(program);
        em.getTransaction().commit();
    }

    public Program persistir(Program program) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            program = em.merge(program);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return program;
    }      
    
}
