/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.Cargo;
import model.Marcacao;
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
    private HtmlSelectOneMenu selectOneMenuCargos;

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

    public List<Usuario> getArrayListUsuarios() throws Exception {
        return (new dao.UsuarioDao()).getArrayListUsuarios();
    }

    public HtmlSelectOneMenu getSelectOneMenuCargos() {
        return selectOneMenuCargos;
    }

    public void setSelectOneMenuCargos(HtmlSelectOneMenu selectOneMenuCargos) {
        this.selectOneMenuCargos = selectOneMenuCargos;
    }

    public void removerUsuario() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        SessaoSistemaMB sessaoSistemaMB = (SessaoSistemaMB) req.getSession().getAttribute("sessaoSistemaMB");

        try {
            if (Objects.equals(sessaoSistemaMB.getUsuario().getId(), this.usuario.getId())) {
                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_WARN,
                        "Não é possível excluir o seu usuário!",
                        "",
                        "mensagemUsuarios");
            } else {
                boolean blnRetorno = (new dao.UsuarioDao()).remover(this.usuario);
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

            //Compatibilidade JPA:
            //Buscar a senha para salvar o usuário no banco
            objUsuario = (new dao.UsuarioDao()).buscarPorId(this.usuario.getId());
            this.usuario.setSenha(objUsuario.getSenha());

            //objeto enviado via post
            (new dao.UsuarioDao()).salvar(this.usuario);

            //Para fins de segurança, seta novamente a senha do usuário no Bean para NULL
            this.usuario.setSenha(null);

            this.AtualizarUsuarioSistema();
            
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
            Usuario objUsuario = (new dao.UsuarioDao()).buscarPorCpf(usuario.getCpf(), -1L);

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
            objUsuario = (new dao.UsuarioDao()).buscarPorNome(usuario.getNome().trim(), -1L);

            if (objUsuario != null) {

                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_WARN,
                        "Nome cadastrado para outro usuário!",
                        "",
                        "mensagemUsuarios");
                return "/faces/sistema/admin/usuario_novo";

            }

            //Verifica se o usuario de sistema foi cadastrado para outro usuário
            objUsuario = (new dao.UsuarioDao()).buscarPorUsuarioSistema(usuario.getUsuarioSistema().trim(), -1L);

            if (objUsuario != null) {

                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_WARN,
                        "Username cadastrado para outro usuário!",
                        "",
                        "mensagemUsuarios");
                return "/faces/sistema/admin/usuario_novo";

            }

            //objeto enviado via post
            (new dao.UsuarioDao()).salvar(usuario);

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
                    "Não foi possível inserir o usuário.",
                    e.getMessage(),
                    "mensagemUsuarios");
            return "/faces/sistema/admin/usuario_novo";
        }
    }

//    public String incluirRegistroUsuarioAtual() {
//        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        SessaoSistemaMB sessaoSistemaMB = (SessaoSistemaMB) req.getSession().getAttribute("sessaoSistemaMB");
//
//        try {
//            Date dataRegistro = new Date();
//
//            if ((new dao.MarcacaoDao().inserir(new Marcacao(dataRegistro, TipoMarcacao.MARCACAO_WEB, null, sessaoSistemaMB.getUsuario())))) {
//                //Incluiu o registro
//                util.Util.FacesContextAddMessage(
//                        FacesMessage.SEVERITY_INFO,
//                        "Marcação efetuada [" + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataRegistro)) + "].",
//                        "",
//                        "mensagemUsuarios");
//                return "/faces/sistema/index";
//            } else {
//                //Não incluiu o registro
//                util.Util.FacesContextAddMessage(
//                        FacesMessage.SEVERITY_WARN,
//                        "Não foi possível incluir a marcação!",
//                        "",
//                        "mensagemUsuarios");
//                return "/faces/sistema/index";
//            }
//        } catch (Exception e) {
//            util.Util.FacesContextAddMessage(
//                    FacesMessage.SEVERITY_ERROR,
//                    "Não foi possível efetuar a marcação.",
//                    e.getMessage(),
//                    "mensagemUsuarios");
//            return "/faces/sistema/index";
//        }
//    }

    public Cargo findCargoByNome(String value) {
        Cargo cargo = null;
        try {
            cargo = (new dao.CargoDao().buscar(value));
        } catch (Exception ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cargo;
    }

    public void AtualizarUsuarioSistema() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        SessaoSistemaMB sessaoSistemaMB = (SessaoSistemaMB) req.getSession().getAttribute("sessaoSistemaMB");
        if (Objects.equals(sessaoSistemaMB.getUsuario().getId(), this.usuario.getId())) {
            sessaoSistemaMB.setUsuario(this.usuario);
        }
    }
}
