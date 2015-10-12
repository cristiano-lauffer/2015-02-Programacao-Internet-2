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
import model.Cargo;

/**
 *
 * @author Cristiano
 */
public class CargoDao {

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

    public ArrayList<Cargo> getArrayListCargos() throws Exception {
        ArrayList<Cargo> listaUsuarios = new ArrayList<Cargo>();

        try {
            String sql = "SELECT * FROM cargos ORDER BY 1 ASC";
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Cargo usuario = new Cargo(
                        resultado.getInt("id"),
                        resultado.getString("nome_cargo")
                );

                listaUsuarios.add(usuario);
            }
            fecharConexao();

            return listaUsuarios;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    /**
     * *
     *
     * @param cargo
     * @return
     * @throws Exception
     */
    public boolean inserir(Cargo cargo) throws Exception {
        try {
            String sql = "INSERT INTO cargos (nome_cargo) VALUES(?)";
            conectar(sql);
            comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            comando.setString(1, cargo.getNomeCargo());
            comando.executeUpdate();
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                cargo.setId(resultado.getInt(1));
            }
            fecharConexao();

            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }
    
    public boolean editar(Cargo cargo) throws Exception {
        try {
            String sql = "UPDATE cargos SET nome_cargo=? WHERE id=?";
            conectar(sql);
            comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            comando.setString(1, cargo.getNomeCargo());
            comando.setInt(2, cargo.getId());
            comando.executeUpdate();

            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }
    
    public int excluir(Cargo cargo) throws Exception {
        try {
            String sql = "DELETE FROM cargos WHERE id=?";
            conectar(sql);
            comando.setInt(1, cargo.getId());
            comando.executeUpdate();
            int intAfectedRows = comando.getUpdateCount();
            fecharConexao();

            return intAfectedRows;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public Cargo buscar(String nomeCargo) throws Exception {
        Cargo cargo = null;

        try {
            String sql = "SELECT * FROM cargos WHERE nome_cargo=?";
            conectar(sql);
            comando.setString(1, nomeCargo);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                cargo = new Cargo(
                        resultado.getInt("id"),
                        resultado.getString("nome_cargo")
                );
            }
            fecharConexao();

            return cargo;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public Cargo buscar(String nomeCargo, int id) throws Exception {
        Cargo cargo = null;

        try {
            String sql = "SELECT * FROM cargos WHERE nome_cargo=? AND id<> ?";
            conectar(sql);
            comando.setString(1, nomeCargo);
            comando.setInt(2, id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                cargo = new Cargo(
                        resultado.getInt("id"),
                        resultado.getString("nome_cargo")
                );
            }
            fecharConexao();

            return cargo;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }
}
