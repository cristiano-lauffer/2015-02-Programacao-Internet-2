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
import model.Usuario;
import util.JpaUtil;

/**
 *
 * @author Cristiano
 */
public class UsuarioDao {

    public boolean salvar(Usuario usuario) throws Exception {
        try {
            EntityManager em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            if (usuario.getId() == null) {
                em.persist(usuario);
            } else {
                em.merge(usuario);
            }
            em.getTransaction().commit();
            em.close();

            return true;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public boolean remover(Usuario usuario) throws Exception {
        try {
            EntityManager em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(usuario));
            em.getTransaction().commit();
            em.close();

            return true;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public List<Usuario> getArrayListUsuarios() throws Exception {
        List<Usuario> listaUsuarios;

        try {
            EntityManager em = JpaUtil.getEntityManager();
            listaUsuarios = em.createQuery("SELECT u FROM Usuario u ORDER BY u.id").getResultList();
            em.close();

            return listaUsuarios;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    /**
     * *
     * Busca um usuário com base no user name e senha
     *
     * @param usuarioSistema
     * @param senha
     * @return
     * @throws java.lang.Exception
     */
    public Usuario buscar(String usuarioSistema, String senha) throws Exception {
        Usuario usuario = null;

        try {
            EntityManager em = JpaUtil.getEntityManager();
            Query query = em.createQuery("SELECT u FROM Usuario u "
                    + "WHERE u.usuarioSistema LIKE :usuarioSistema "
                    + "AND u.senha LIKE :senha ");
            query.setParameter("usuarioSistema", usuarioSistema);
            query.setParameter("senha", senha);
            List<Usuario> listaUsuarios = query.getResultList();

            for (Usuario usuarioRes : listaUsuarios) {
                usuario = usuarioRes;
                break;
            }

            em.close();

            return usuario;

        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    /**
     * *
     * Busca um usuário por CPF, ignorando o ID passado
     *
     * @param cpf
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public Usuario buscarPorCpf(String cpf, Long id) throws Exception {
        Usuario usuario = null;

        try {
            EntityManager em = JpaUtil.getEntityManager();
            Query query = em.createQuery("SELECT u FROM Usuario u "
                    + "WHERE u.cpf LIKE :cpf "
                    + "AND u.id <> :id ");
            query.setParameter("cpf", cpf);
            query.setParameter("id", id);
            List<Usuario> listaUsuarios = query.getResultList();

            for (Usuario usuarioRes : listaUsuarios) {
                usuario = usuarioRes;
                break;
            }

            em.close();

            return usuario;

        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    /**
     * *
     * Busca um usuário por Nome, ignorando o ID passado
     *
     * @param nome
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public Usuario buscarPorNome(String nome, Long id) throws Exception {
        Usuario usuario = null;

        try {
            EntityManager em = JpaUtil.getEntityManager();
            Query query = em.createQuery("SELECT u FROM Usuario u "
                    + "WHERE u.nome LIKE :nome "
                    + "AND u.id <> :id ");
            query.setParameter("nome", nome);
            query.setParameter("id", id);
            List<Usuario> listaUsuarios = query.getResultList();

            for (Usuario usuarioRes : listaUsuarios) {
                usuario = usuarioRes;
                break;
            }

            em.close();

            return usuario;

        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    /**
     * *
     * Busca um usuário por Usuário do sistema, ignorando o ID passado
     *
     * @param usuarioSistema
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public Usuario buscarPorUsuarioSistema(String usuarioSistema, Long id) throws Exception {
        Usuario usuario = null;

        try {
            EntityManager em = JpaUtil.getEntityManager();
            Query query = em.createQuery("SELECT u FROM Usuario u "
                    + "WHERE u.usuarioSistema LIKE :usuarioSistema "
                    + "AND u.id <> :id ");
            query.setParameter("usuarioSistema", usuarioSistema);
            query.setParameter("id", id);
            List<Usuario> listaUsuarios = query.getResultList();

            for (Usuario usuarioRes : listaUsuarios) {
                usuario = usuarioRes;
                break;
            }

            em.close();

            return usuario;

        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public Usuario buscarPorId(Long id) throws Exception {
        Usuario usuario = null;

        try {
            EntityManager em = JpaUtil.getEntityManager();
            Query query = em.createQuery("SELECT u FROM Usuario u "
                    + "WHERE u.id = :id ");
            query.setParameter("id", id);
            List<Usuario> listaUsuarios = query.getResultList();

            for (Usuario usuarioRes : listaUsuarios) {
                usuario = usuarioRes;
                break;
            }

            em.close();

            return usuario;

        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

}
