/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.MarcacaoHorario;
import model.TipoMarcacao;
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

    public ArrayList<Usuario> getArrayListUsuarios() throws Exception {
        return (new dao.UsuarioDao()).getArrayListUsuarios();
    }

    public void removerUsuario() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        SessaoSistemaMB sessaoSistemaMB = (SessaoSistemaMB) req.getSession().getAttribute("sessaoSistemaMB");

        try {
            if (sessaoSistemaMB.getUsuario().getId() == this.usuario.getId()) {
                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_WARN,
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
        } catch (Exception e) {
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Não foi possível excluir o usuário.",
                    e.getMessage(),
                    "mensagemUsuarios");
        }
    }

    public String editarUsuario() {
        try {
            //Verifica se o cpf informado é válido
            if (!util.Util.isCPF(usuario.getCpf().trim())) {
                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_WARN,
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
                            FacesMessage.SEVERITY_WARN,
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
                        FacesMessage.SEVERITY_WARN,
                        "Nome cadastrado para outro usuário!",
                        "",
                        "mensagemUsuarios");
                return "/faces/sistema/admin/usuario_editar";

            }

            //Verifica se o usuario de sistema foi cadastrado para outro usuário
            objUsuario = (new dao.UsuarioDao()).buscarPorUsuarioSistema(usuario.getUsuarioSistema().trim(), this.usuario.getId());

            if (objUsuario != null) {

                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_WARN,
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
        } catch (Exception e) {
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Não foi possível editar o usuário.",
                    e.getMessage(),
                    "mensagemUsuarios");
            return "/faces/sistema/admin/usuario_editar";
        }
    }

    public String inserirUsuario() {

        try {
            //Verifica se o cpf informado é válido
            if (!util.Util.isCPF(usuario.getCpf().trim())) {
                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_WARN,
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
                            FacesMessage.SEVERITY_WARN,
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
                        FacesMessage.SEVERITY_WARN,
                        "Nome cadastrado para outro usuário!",
                        "",
                        "mensagemUsuarios");
                return "/faces/sistema/admin/usuario_novo";

            }

            //Verifica se o usuario de sistema foi cadastrado para outro usuário
            objUsuario = (new dao.UsuarioDao()).buscarPorUsuarioSistema(usuario.getUsuarioSistema().trim(), -1);

            if (objUsuario != null) {

                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_WARN,
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
        } catch (Exception e) {
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Não foi possível editar o usuário.",
                    e.getMessage(),
                    "mensagemUsuarios");
            return "/faces/sistema/admin/usuario_novo";
        }
    }

    public String incluirRegistroUsuarioAtual() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        SessaoSistemaMB sessaoSistemaMB = (SessaoSistemaMB) req.getSession().getAttribute("sessaoSistemaMB");

        try {
            Date dataRegistro = new Date();

            if ((new dao.MarcacaoDao().inserir(new MarcacaoHorario(dataRegistro, TipoMarcacao.MARCACAO_WEB, null, sessaoSistemaMB.getUsuario())))) {
                //Incluiu o registro
                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Marcação efetuada [" + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataRegistro)) + "].",
                        "",
                        "mensagemUsuarios");
                return "/faces/sistema/index";
            } else {
                //Não incluiu o registro
                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_WARN,
                        "Não foi possível incluir a marcação!",
                        "",
                        "mensagemUsuarios");
                return "/faces/sistema/index";
            }
        } catch (Exception e) {
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Não foi possível efetuar a marcação.",
                    e.getMessage(),
                    "mensagemUsuarios");
            return "/faces/sistema/index";
        }
    }
}
