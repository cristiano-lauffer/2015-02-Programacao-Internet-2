/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author Cristiano
 */
public class Usuario {

    private static int idGenerated = 0;
    private int id;
    private String nome;
    private String cpf;
    private String usuarioSistema;
    private String senha;
    private boolean administrador;

    public Usuario(int id, String nome, String cpf, String usuarioSistema, String senha, boolean administrador) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.usuarioSistema = usuarioSistema;
        this.senha = senha;
        this.administrador = administrador;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        /*
         if (!Objects.equals(this.nome, other.nome)) {
         return false;
         }
         if (!Objects.equals(this.cpf, other.cpf)) {
         return false;
         }
         */
//        if (!Objects.equals(this.usuarioSistema, other.usuarioSistema)) {
//            return false;
//        }
//
//        if (!Objects.equals(this.senha, other.senha)) {
//            return false;
//        }

        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public int GenerateNewId() {
        return ++idGenerated;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }

}
