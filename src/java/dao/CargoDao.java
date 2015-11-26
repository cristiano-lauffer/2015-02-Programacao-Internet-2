/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import model.Cargo;
import util.JpaUtil;

/**
 *
 * @author Cristiano
 */
public class CargoDao {

    public boolean salvar(Cargo cargo) throws Exception {
        try {
            EntityManager em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            if (cargo.getId() == null) {
                em.persist(cargo);
            } else {
                em.merge(cargo);
            }
            em.getTransaction().commit();
            em.close();

            return true;
        } catch (Exception ex) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public boolean remover(Cargo cargo) throws Exception {
        try {
            EntityManager em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(cargo));
            em.getTransaction().commit();
            em.close();

            return true;
        } catch (Exception ex) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public List<Cargo> getArrayListCargos() throws Exception {
        List<Cargo> listaCargos;

        try {
            EntityManager em = JpaUtil.getEntityManager();
            listaCargos = em.createQuery("SELECT c FROM Cargo c").getResultList();
            em.close();

            return listaCargos;
        } catch (Exception ex) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public List<Object[]> getArrayListCargosSum() throws Exception {
        List<Object[]> listaCargos;

        try {
            EntityManager em = JpaUtil.getEntityManager();
            //Query query = em.createQuery("SELECT c, _c FROM Marcacao m "//);
//            TypedQuery<Object[]> query = em.createQuery("SELECT c.nome, SUM(HOUR(m.dtSaida)) FROM Cargo c "//);
//                    + "INNER JOIN Usuario u INNER JOIN Marcacao m "//);
//                    + "WHERE "//);
//                    + "m.dtEntrada IS NOT NULL AND m.dtSaida IS NOT NULL "//);
//                    + "GROUP BY c.nome ", Object[].class);
            TypedQuery<Object[]> query = em.createQuery(
                    "SELECT "
                    + "c.nome, "
                    + "SUM((m.dtSaida)) "
                    //+ "CURRENT_TIME() "
                    //+ "FUNC('TIME_TO_SEC, m.dtSaida) "
                    //+ "SUM(m.dtSaida) "
                    + "FROM Marcacao m "
                    + "JOIN m.usuario u "
                    + "JOIN u.cargo c "
                    + "GROUP BY c.nome ",
                    Object[].class);
            listaCargos = query.getResultList();

            //
            em.close();

            return listaCargos;
        } catch (Exception ex) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public Cargo buscar(String nomeCargo) throws Exception {
        Cargo cargo = null;

        try {
            EntityManager em = JpaUtil.getEntityManager();
            Query query = em.createQuery("SELECT c FROM Cargo c "
                    + "WHERE UPPER(c.nome) LIKE :nomeCargo "
                    + "ORDER BY c.nome");
            query.setParameter("nomeCargo", nomeCargo.toUpperCase());
            List<Cargo> listaCargo = query.getResultList();

            for (Cargo cargoRes : listaCargo) {
                cargo = cargoRes;
                break;
            }

            em.close();

            return cargo;

        } catch (Exception ex) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public Cargo buscar(String nomeCargo, Long id) throws Exception {
        Cargo cargo = null;

        try {
            EntityManager em = JpaUtil.getEntityManager();
            Query query = em.createQuery("SELECT c FROM Cargo c "
                    + "WHERE UPPER(c.nome) LIKE :nomeCargo "
                    + "AND c.id <> :id "
                    + "ORDER BY c.nome");
            query.setParameter("nomeCargo", nomeCargo.toUpperCase());
            query.setParameter("id", id);
            List<Cargo> listaCargo = query.getResultList();

            for (Cargo cargoRes : listaCargo) {
                cargo = cargoRes;
                break;
            }

            em.close();

            return cargo;

        } catch (Exception ex) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public Cargo buscar(int id) throws Exception {
        Cargo cargo;

        try {
            EntityManager em = JpaUtil.getEntityManager();
            cargo = em.find(Cargo.class, id);
            em.close();

            return cargo;

        } catch (Exception ex) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }
}
