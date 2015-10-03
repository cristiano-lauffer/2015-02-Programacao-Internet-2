/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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
    private HtmlDataTable dataTableUsuarios;

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

    public HtmlDataTable getDataTableUsuarios() {
        return dataTableUsuarios;
    }

    public void setDataTableUsuarios(HtmlDataTable dataTableUsuarios) {
        this.dataTableUsuarios = dataTableUsuarios;
    }

    public void removerUsuario() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        SessaoSistemaMB sessaoSistemaMB = (SessaoSistemaMB) req.getSession().getAttribute("sessaoSistemaMB");
        
        Usuario usuarioRemover = sessaoSistemaMB.BuscarUsuarioPorCpf(this.usuario.getCpf());
        
        if(usuarioRemover == null){
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage mensagem = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Não foi possível remover o usuário!",
                    "");
            contexto.addMessage("mensagemUsuarios", mensagem);
            return;
        }

        if (sessaoSistemaMB.getUsuario().getCpf().equals(usuarioRemover.getCpf())) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage mensagem = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Não é possível excluir o seu usuário!",
                    "");
            contexto.addMessage("mensagemUsuarios", mensagem);
        } else {
            sessaoSistemaMB.getListaUsuarios().remove(this.usuario);
        }
    }

    public void editarUsuario() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        SessaoSistemaMB sessaoSistemaMB = (SessaoSistemaMB) req.getSession().getAttribute("sessaoSistemaMB");

    }

    public void adicionarUsuario() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        SessaoSistemaMB sessaoSistemaMB = (SessaoSistemaMB) req.getSession().getAttribute("sessaoSistemaMB");

        //sessaoSistemaMB.getListaUsuarios().add(this.usuario);
    }
}
