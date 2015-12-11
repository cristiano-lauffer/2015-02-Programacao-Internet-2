/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristiano
 */
@XmlRootElement
public class Usuario implements Serializable {

    private boolean administrador;
    private Cargo cargo;
    private String cpf;
    private Long id;
    private String nome;
    private String senha;
    private String usuarioSistema;    
    
    //private String administrador;

//    public String getAdministrador() {
//        return administrador;
//    }
//
//    public void setAdministrador(String administrador) {
//        this.administrador = administrador;
//    }
    

    public Usuario(Long id, String nome, String cpf, String usuarioSistema, String senha, boolean administrador) {
    //public Usuario(Long id, String nome, String cpf, String usuarioSistema, String senha, String administrador) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.usuarioSistema = usuarioSistema;
        this.senha = senha;
        this.administrador = administrador;
    }

    public Usuario(Long id, String nome, String cpf, String usuarioSistema, String senha, boolean administrador, Cargo cargo) {
    //public Usuario(Long id, String nome, String cpf, String usuarioSistema, String senha, String administrador, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.usuarioSistema = usuarioSistema;
        this.senha = senha;
        this.administrador = administrador;
        this.cargo = cargo;
    }
    
    public Usuario(String usuarioSistema, String senha) {
        this.usuarioSistema = usuarioSistema;
        this.senha = senha;
    }

    public Usuario() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(String usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public Cargo getCargo() {
        return this.cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }

}
