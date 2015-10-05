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

    public ArrayList<Usuario> getArrayListUsuarios() {
        return (new dao.UsuarioDao()).getArrayListUsuarios();
    }

    public void removerUsuario() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        SessaoSistemaMB sessaoSistemaMB = (SessaoSistemaMB) req.getSession().getAttribute("sessaoSistemaMB");

        if (sessaoSistemaMB.getUsuario().getId() == this.usuario.getId()) {
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Não é possível excluir o seu usuário!",
                    "",
                    "mensagemUsuarios");
        } else {
            int intRetorno = (new dao.UsuarioDao()).excluir(this.usuario);
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_INFO,
                    "Usuário removido!",
                    "",
                    "mensagemUsuarios");
        }
    }

    public String editarUsuario() {
        //Verifica se o cpf informado é válido
        if (!util.Util.isCPF(usuario.getCpf().trim())) {
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "O cpf informádo é inválido!",
                    "",
                    "mensagemUsuarios");
            return "/faces/sistema/admin/usuario_editar";
        }

        //Verifica se o cpf foi cadastrado para outro usuário
        Usuario objUsuario = (new dao.UsuarioDao()).buscarPorCpf(usuario.getCpf(), this.usuario.getId());

        if (objUsuario != null) {
            if (usuario.getCpf().trim().equals(objUsuario.getCpf())) {
                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Cpf cadastrado para outro usuário!",
                        "",
                        "mensagemUsuarios");
                return "/faces/sistema/admin/usuario_editar";
            }
        }

        //Verifica se o nome foi cadastrado para outro usuário
        objUsuario = (new dao.UsuarioDao()).buscarPorNome(usuario.getNome().trim(), this.usuario.getId());

        if (objUsuario != null) {

            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Nome cadastrado para outro usuário!",
                    "",
                    "mensagemUsuarios");
            return "/faces/sistema/admin/usuario_editar";

        }

        //Verifica se o usuario de sistema foi cadastrado para outro usuário
        objUsuario = (new dao.UsuarioDao()).buscarPorUsuarioSistema(usuario.getUsuarioSistema().trim(), this.usuario.getId());

        if (objUsuario != null) {

            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Username cadastrado para outro usuário!",
                    "",
                    "mensagemUsuarios");
            return "/faces/sistema/admin/usuario_editar";

        }

        //objeto enviado via post
        (new dao.UsuarioDao()).editar(usuario);

        util.Util.FacesContextAddMessage(
                FacesMessage.SEVERITY_INFO,
                "Usuário [" + this.usuario.toString() + "] editado!",
                "",
                "mensagemUsuarios");

        //return "/faces/sistema/admin/usuarios?faces-redirect=true";
        return "/faces/sistema/admin/usuarios";
    }

    public String inserirUsuario() {

        //Verifica se o cpf informado é válido
        if (!util.Util.isCPF(usuario.getCpf().trim())) {
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "O cpf informádo é inválido!",
                    "",
                    "mensagemUsuarios");
            return "/faces/sistema/admin/usuario_novo";
        }

        //Verifica se o cpf foi cadastrado para outro usuário
        Usuario objUsuario = (new dao.UsuarioDao()).buscarPorCpf(usuario.getCpf(), -1);

        if (objUsuario != null) {
            if (usuario.getCpf().trim().equals(objUsuario.getCpf())) {
                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Cpf cadastrado para outro usuário!",
                        "",
                        "mensagemUsuarios");
                return "/faces/sistema/admin/usuario_novo";
            }
        }

        //Verifica se o nome foi cadastrado para outro usuário
        objUsuario = (new dao.UsuarioDao()).buscarPorNome(usuario.getNome().trim(), -1);

        if (objUsuario != null) {

            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Nome cadastrado para outro usuário!",
                    "",
                    "mensagemUsuarios");
            return "/faces/sistema/admin/usuario_novo";

        }

        //Verifica se o usuario de sistema foi cadastrado para outro usuário
        objUsuario = (new dao.UsuarioDao()).buscarPorUsuarioSistema(usuario.getUsuarioSistema().trim(), -1);

        if (objUsuario != null) {

            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Username cadastrado para outro usuário!",
                    "",
                    "mensagemUsuarios");
            return "/faces/sistema/admin/usuario_novo";

        }

        //objeto enviado via post
        (new dao.UsuarioDao()).inserir(usuario);

        util.Util.FacesContextAddMessage(
                FacesMessage.SEVERITY_INFO,
                "Usuário [" + this.usuario.toString() + "] cadastrado!",
                "",
                "mensagemUsuarios");

        //return "/faces/sistema/admin/usuarios?faces-redirect=true";
        return "/faces/sistema/admin/usuarios";
    }
}
