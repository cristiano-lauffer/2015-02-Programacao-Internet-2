/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Usuario;

/**
 *
 * @author Cristiano
 */
@ManagedBean
@RequestScoped
public class UsuarioMB {

    private Usuario usuario;
    private boolean logado;

    /**
     * Creates a new instance of UsuarioMB
     */
    public UsuarioMB() {
        logado = false;
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isLogado() {
        return logado;
    }

}
