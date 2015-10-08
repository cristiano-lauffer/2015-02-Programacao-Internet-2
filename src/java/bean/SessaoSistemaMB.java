/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

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

    private SessaoSistema sessaoSistema;

    /**
     * Creates a new instance of SessaoSistemaMB
     */
    public SessaoSistemaMB() {

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

    public String realizarLogin() {
        try {
            Usuario objUsuario = (new dao.UsuarioDao()).buscar(this.sessaoSistema.getUsuario().getUsuarioSistema(), this.sessaoSistema.getUsuario().getSenha());

            if (objUsuario != null) {
                this.sessaoSistema.getUsuario().setId(objUsuario.getId());
                this.sessaoSistema.getUsuario().setNome(objUsuario.getNome());
                this.sessaoSistema.getUsuario().setCpf(objUsuario.getCpf());
                this.sessaoSistema.getUsuario().setAdministrador(objUsuario.isAdministrador());
                //this.sessaoSistema.getUsuario().setSenha("");
                this.sessaoSistema.setLogado(true);
                return "/faces/sistema/index?faces-redirect=true";
            } else {
                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_WARN,
                        "Login Inválido!",
                        "Usuário e/ou senha estão errados! Digite sua senha novamente!",
                        "mensagemLogin"
                );

                return "login";
            }
        } catch (Exception e) {
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Falha no login. Favor entrar em contato com o administrador do sistema.",
                    e.getMessage(),
                    "mensagemLogin"
            );
            return "login";
        }
    }

    public String realizarLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //#bug
        //o redirecionamento estava sendo feito para a pasta corrente, não para a raiz
        return ("/faces/login?faces-redirect=true");
    }

}
