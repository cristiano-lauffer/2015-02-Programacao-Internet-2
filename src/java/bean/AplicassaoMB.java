/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import model.Usuario;

/**
 *
 * @author Cristiano
 */
@ManagedBean
@ApplicationScoped
public class AplicassaoMB {

    private ArrayList<Usuario> listaUsuarios;

    /**
     * Creates a new instance of AplicassaoMB
     */
    public AplicassaoMB() {
        listaUsuarios = new ArrayList<Usuario>();
        listaUsuarios.add(new Usuario((new Usuario()).GenerateNewId(), "Cristiano", "01040337031", "clauffer", "123", true));
        listaUsuarios.add(new Usuario((new Usuario()).GenerateNewId(), "Leonardo", "12345678912", "ldutra", "123", false));
        listaUsuarios.add(new Usuario((new Usuario()).GenerateNewId(), "Administrador", "78912345678", "admin", "12345", false));
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario BuscarUsuario(String usuarioSistema, String senha) {
        Usuario objUsuario = null;

        for (Usuario user : this.listaUsuarios) {
            if (user.getUsuarioSistema().equals(usuarioSistema)) {
                if (user.getSenha().equals(senha)) {
                    objUsuario = user;
                    break;
                } else {
                    return null;
                }
            }
        }

        return objUsuario;
    }

    public Usuario BuscarUsuarioPorCpf(String cpf) {
        Usuario objUsuario = null;

        for (Usuario user : this.listaUsuarios) {
            if (user.getCpf().equals(cpf)) {
                objUsuario = user;
                break;
            }
        }

        return objUsuario;
    }

}
