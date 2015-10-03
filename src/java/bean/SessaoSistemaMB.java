/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.SessaoSistema;
import model.Usuario;

/**
 *
 * @author Cristiano
 */
@ManagedBean
@SessionScoped
public class SessaoSistemaMB {

    private ArrayList<Usuario> listaUsuarios;
    private SessaoSistema sessaoSistema;

    /**
     * Creates a new instance of SessaoSistemaMB
     */
    public SessaoSistemaMB() {

        listaUsuarios = new ArrayList<Usuario>();
        listaUsuarios.add(new Usuario("Cristiano", "01040337031", "clauffer", "123", true));
        listaUsuarios.add(new Usuario("Leonardo", "12345678912", "ldutra", "123", false));
        listaUsuarios.add(new Usuario("Administrador", "78912345678", "admin", "12345", false));

        sessaoSistema = new SessaoSistema();
        sessaoSistema.setUsuario(new Usuario());
        sessaoSistema.setLogado(false);
    }

    public Usuario getUsuario() {
        return this.sessaoSistema.getUsuario();
    }

    public void setUsuario(Usuario usuario) {
        this.sessaoSistema.setUsuario(usuario);
    }

    public boolean isLogado() {
        return this.sessaoSistema.isLogado();
    }

    private Usuario BuscarUsuario(String usuarioSistema, String senha) {
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

    public String realizarLogin() {
        Usuario objUsuario = this.BuscarUsuario(this.sessaoSistema.getUsuario().getUsuarioSistema(), this.sessaoSistema.getUsuario().getSenha());

        //if (listaUsuarios.contains(usuario)) {
        if (objUsuario != null) {
            this.sessaoSistema.getUsuario().setNome(objUsuario.getNome());
            this.sessaoSistema.getUsuario().setCpf(objUsuario.getCpf());
            this.sessaoSistema.getUsuario().setAdministrador(objUsuario.isAdministrador());
            //this.sessaoSistema.getUsuario().setSenha("");
            this.sessaoSistema.setLogado(true);
            return "/faces/sistema/index?faces-redirect=true";
        } else {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage mensagem = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Login Inválido!",
                    "Usuário e/ou senha estão errados! Digite sua senha novamente!");
            contexto.addMessage("mensagemLogin", mensagem);

            return "login";
        }
    }

    public String realizarLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return ("index?faces-redirect=true");
    }

}
