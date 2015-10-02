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
import model.Usuario;

/**
 *
 * @author Cristiano
 */
@ManagedBean
@SessionScoped
public class UsuarioMB {

    private ArrayList<Usuario> listaUsuarios;
    private Usuario usuario;
    private boolean logado;

    /**
     * Creates a new instance of UsuarioMB
     */
    public UsuarioMB() {
        logado = false;
        listaUsuarios = new ArrayList<Usuario>();
        listaUsuarios.add(new Usuario("Cristiano", "01040337031", "clauffer", "123"));
        listaUsuarios.add(new Usuario("Leonardo", "12345678912", "ldutra", "123"));
        listaUsuarios.add(new Usuario("Administrador", "78912345678", "admin", "12345"));

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

    public String verificaLogin() {
        Usuario objUsuario = this.BuscarUsuario(this.usuario.getUsuarioSistema(), this.usuario.getSenha());
        
        //if (listaUsuarios.contains(usuario)) {
        if(objUsuario != null){
            this.usuario.setNome(objUsuario.getNome());
            this.usuario.setCpf(objUsuario.getCpf());
            this.usuario.setSenha("");
            logado = true;
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

    public String realizaLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        logado = false;
        return ("index?faces-redirect=true");
    }
    
    private Usuario BuscarUsuario(String usuarioSistema, String senha){
        Usuario objUsuario = null;
        
        for (Usuario user : listaUsuarios) {
            if(user.getUsuarioSistema().equals(usuarioSistema)){
                if(user.getSenha().equals(senha)){
                    objUsuario = user;
                    break;
                } else {
                    return null;
                }
            }
        }
        
        return objUsuario;
    }

}
