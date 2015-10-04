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
        listaUsuarios.add(new Usuario((new Usuario()).GenerateNewId(), "Cristiano", "01040337031", "clauffer", "123", true));
        listaUsuarios.add(new Usuario((new Usuario()).GenerateNewId(), "Leonardo", "12345678912", "ldutra", "123", false));
        listaUsuarios.add(new Usuario((new Usuario()).GenerateNewId(), "Administrador", "78912345678", "admin", "12345", false));

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

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
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

    Usuario BuscarUsuarioPorId(int id) {
        for (Usuario user : this.listaUsuarios) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
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
        //#bug
        //o redirecionamento estava sendo feito para a pasta corrente, não para a raiz
        return ("/faces/login?faces-redirect=true");
    }

    public boolean editarUsuario(Usuario usuario) {

        Usuario objUsuarioEditar = this.BuscarUsuarioPorId(usuario.getId());

        objUsuarioEditar.setNome(usuario.getNome());
        objUsuarioEditar.setCpf(usuario.getCpf());
        objUsuarioEditar.setUsuarioSistema(usuario.getUsuarioSistema());

        return true;
    }

    public boolean adicionarUsuario(Usuario usuario) {

        usuario.setId(usuario.GenerateNewId());
        this.listaUsuarios.add(usuario);

        return true;
    }

}
