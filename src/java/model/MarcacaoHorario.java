/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Cristiano
 */
public class MarcacaoHorario {

    private Date dtMarcacao;
    private TipoMarcacao idTipo;
    private Date dtAlteracao;
    private Usuario usuario;

    public MarcacaoHorario(Date dtMarcacao, TipoMarcacao idTipo, Date dtAlteracao, Usuario usuario) {
        this.dtMarcacao = dtMarcacao;
        this.idTipo = idTipo;
        this.dtAlteracao = dtAlteracao;
        this.usuario = usuario;
    }

    public MarcacaoHorario() {
    }

    public Date getDtMarcacao() {
        return dtMarcacao;
    }

    public void setDtMarcacao(Date dtMarcacao) {
        this.dtMarcacao = dtMarcacao;
    }

    public TipoMarcacao getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoMarcacao idTipo) {
        this.idTipo = idTipo;
    }

    public Date getDtAlteracao() {
        return dtAlteracao;
    }

    public void setDtAlteracao(Date dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
