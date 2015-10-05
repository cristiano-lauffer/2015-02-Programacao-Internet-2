/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import banco.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;

/**
 *
 * @author Cristiano
 */
public class UsuarioDao {

    private Connection conexao;
    private PreparedStatement comando;

    private void conectar(String sql) throws ClassNotFoundException, SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
    }

    private void fecharConexao() throws SQLException {
        comando.close();
        conexao.close();
    }

    /**
     * *
     * Busca um usuário com base no user name e senha
     *
     * @param usuarioSistema
     * @param senha
     * @return
     */
    public Usuario buscar(String usuarioSistema, String senha) {
        Usuario usuarioRetorno = null;

        try {
            String sql = "SELECT * FROM usuarios WHERE usuarioSistema=? AND senha=?";
            conectar(sql);
            comando.setString(1, usuarioSistema);
            comando.setString(2, senha);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                usuarioRetorno = new Usuario(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("cpf"),
                        resultado.getString("usuariosistema"),
                        resultado.getString("senha"),
                        resultado.getBoolean("administrador")
                );
            }
            fecharConexao();

            return usuarioRetorno;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarioRetorno;
    }

    /**
     * *
     * Busca um usuário por CPF, ignorando o ID passado
     *
     * @param cpf
     * @param id
     * @return
     */
    public Usuario buscarPorCpf(String cpf, int id) {
        Usuario usuarioRetorno = null;

        try {
            String sql = "SELECT * FROM usuarios WHERE cpf=? AND id<>?";
            conectar(sql);
            comando.setString(1, cpf);
            comando.setInt(2, id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                usuarioRetorno = new Usuario(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("cpf"),
                        resultado.getString("usuariosistema"),
                        resultado.getString("senha"),
                        resultado.getBoolean("administrador")
                );
            }
            fecharConexao();

            return usuarioRetorno;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarioRetorno;
    }

    
    /***
     * Busca um usuário por Nome, ignorando o ID passado
     * @param nome
     * @param id
     * @return 
     */
    public Usuario buscarPorNome(String nome, int id) {
        Usuario usuarioRetorno = null;

        try {
            String sql = "SELECT * FROM usuarios WHERE nome=? AND id<>?";
            conectar(sql);
            comando.setString(1, nome);
            comando.setInt(2, id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                usuarioRetorno = new Usuario(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("cpf"),
                        resultado.getString("usuariosistema"),
                        resultado.getString("senha"),
                        resultado.getBoolean("administrador")
                );
            }
            fecharConexao();

            return usuarioRetorno;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarioRetorno;
    }
    
    /***
     * Busca um usuário por Usuário do sistema, ignorando o ID passado
     * @param usuarioSistema
     * @param id
     * @return 
     */
    public Usuario buscarPorUsuarioSistema(String usuarioSistema, int id) {
        Usuario usuarioRetorno = null;

        try {
            String sql = "SELECT * FROM usuarios WHERE usuarioSistema=? AND id<>?";
            conectar(sql);
            comando.setString(1, usuarioSistema);
            comando.setInt(2, id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                usuarioRetorno = new Usuario(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("cpf"),
                        resultado.getString("usuariosistema"),
                        resultado.getString("senha"),
                        resultado.getBoolean("administrador")
                );
            }
            fecharConexao();

            return usuarioRetorno;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarioRetorno;
    }

    public ArrayList<Usuario> getArrayListUsuarios() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();

        try {
            String sql = "SELECT * FROM usuarios ORDER BY 1ASC";
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Usuario usuario = new Usuario(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("cpf"),
                        resultado.getString("usuariosistema"),
                        resultado.getString("senha"),
                        resultado.getBoolean("administrador")
                );

                listaUsuarios.add(usuario);
            }
            fecharConexao();

            return listaUsuarios;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsuarios;
    }

    public boolean inserir(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuarios (nome, cpf, usuarioSistema, senha, administrador) VALUES(?,?,?,?,?)";
            conectar(sql);
            comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getCpf());
            comando.setString(3, usuario.getUsuarioSistema());
            comando.setString(4, usuario.getSenha());
            comando.setBoolean(5, usuario.isAdministrador());
            comando.executeUpdate();
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                usuario.setId(resultado.getInt(1));
            }
            fecharConexao();

            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean editar(Usuario usuario) {
        try {
            String sql = "UPDATE usuarios SET nome=?, cpf=?, usuarioSistema=?, administrador=? WHERE id=?";
            conectar(sql);
            comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getCpf());
            comando.setString(3, usuario.getUsuarioSistema());
            comando.setBoolean(4, usuario.isAdministrador());
            comando.setInt(5, usuario.getId());
            comando.executeUpdate();

            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * *
     *
     * @param usuario
     * @return -1 se não foi possível excluir o usuário
     */
    public int excluir(Usuario usuario) {
        try {
            String sql = "DELETE FROM usuarios WHERE id=?";
            conectar(sql);
            comando.setInt(1, usuario.getId());
            comando.executeUpdate();
            int intAfectedRows = comando.getUpdateCount();
            fecharConexao();

            return intAfectedRows;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

}