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
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MarcacaoHorario;

/**
 *
 * @author Cristiano
 */
public class MarcacaoDao {

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

    public boolean inserir(MarcacaoHorario marcacaoHorario) {
        try {
            String sql = "INSERT INTO marcacoes_horarios (dt_marcacao, id_tipo_marcacao, id_usuario) VALUES (?,?,?)";
            conectar(sql);
            comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            comando.setObject(1, marcacaoHorario.getDtMarcacao(), Types.TIMESTAMP);
            comando.setInt(2, marcacaoHorario.getIdTipo().getValue());
            comando.setLong(3, marcacaoHorario.getUsuario().getId());
            comando.executeUpdate();
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //registroHorario.setDtRegistro((Date)resultado.getObject(1, Types.TIMESTAMP));
            }
            fecharConexao();

            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MarcacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MarcacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
