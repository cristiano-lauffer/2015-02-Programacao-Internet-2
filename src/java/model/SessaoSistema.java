/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Cristiano
 */
public class SessaoSistema {

    private Usuario usuario;
    private boolean logado;

    public SessaoSistema(Usuario usuario, boolean logado) {
        this.usuario = usuario;
        this.logado = logado;
    }

    public SessaoSistema() {
        this.usuario = null;
        this.logado = false;
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

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

}
