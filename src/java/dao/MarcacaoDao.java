/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Marcacao;
import model.Usuario;
import util.JpaUtil;

/**
 *
 * @author Cristiano
 */
public class MarcacaoDao {

    public boolean salvar(Marcacao marcacao) throws Exception {
        try {
            EntityManager em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            if (this.ExisteMarcacao(marcacao)) {
                em.merge(marcacao);
            } else {
                em.persist(marcacao);
            }
            em.getTransaction().commit();
            em.close();

            return true;
        } catch (Exception ex) {
            Logger.getLogger(MarcacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public boolean remover(Marcacao marcacao) throws Exception {
        try {
            EntityManager em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(marcacao));
            em.getTransaction().commit();
            em.close();

            return true;
        } catch (Exception ex) {
            Logger.getLogger(MarcacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public boolean ExisteMarcacao(Marcacao marcacao) throws Exception {
        try {
            EntityManager em = JpaUtil.getEntityManager();
            Query query = em.createQuery("SELECT m FROM Marcacao m "
                    + "WHERE m.dtMarcacao = :data "
                    + "AND m.usuario = :usuario ");
            query.setParameter("data", marcacao.getDtMarcacao());
            query.setParameter("usuario", marcacao.getUsuario());
            List<Marcacao> listaMarcacao = query.getResultList();

            for (Marcacao marcacaoRes : listaMarcacao) {
                em.close();
                return true;
            }

            em.close();

            return false;

        } catch (Exception ex) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }
    
    public Marcacao BuscarMarcacaoDataAtual(Usuario usuario) throws Exception {
        try {
            EntityManager em = JpaUtil.getEntityManager();
            Query query = em.createQuery("SELECT m FROM Marcacao m "
                    + "WHERE m.dtMarcacao = :data "
                    + "AND m.usuario = :usuario ");
            query.setParameter("data", new Date());
            query.setParameter("usuario", usuario);
            List<Marcacao> listaMarcacao = query.getResultList();

            for (Marcacao marcacaoRes : listaMarcacao) {
                em.close();
                return marcacaoRes;
            }

            em.close();

            Marcacao objMarcacao = new Marcacao();
            objMarcacao.setDtMarcacao(new Date());
            return objMarcacao;

        } catch (Exception ex) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

}
