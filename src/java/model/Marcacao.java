/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Cristiano
 */
@Entity
public class Marcacao implements Serializable {

    @Id
    @Temporal(TemporalType.DATE)
    private Date dtMarcacao;
    @Id
    @ManyToOne(optional = false)
    private Usuario usuario;
    @Temporal(TemporalType.TIME)
    @Column(nullable = true)
    private Date dtEntrada;
    @Temporal(TemporalType.TIME)
    @Column(nullable = true)
    private Date dtSaida;

    public Marcacao(Date dtMarcacao, Date dtEntrada, Date dtSaida, Usuario usuario) {
        this.dtMarcacao = dtMarcacao;
        this.dtEntrada = dtEntrada;
        this.dtSaida = dtSaida;
        this.usuario = usuario;
    }

    public Marcacao() {
    }

    public Date getDtMarcacao() {
        return dtMarcacao;
    }

    public void setDtMarcacao(Date dtMarcacao) {
        this.dtMarcacao = dtMarcacao;
    }

    public Date getDtEntrada() {
        return dtEntrada;
    }

    public void setDtEntrada(Date dtEntrada) {
        this.dtEntrada = dtEntrada;
    }

    public Date getDtSaida() {
        return dtSaida;
    }

    public void setDtSaida(Date dtSaida) {
        this.dtSaida = dtSaida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.dtMarcacao);
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
        final Marcacao other = (Marcacao) obj;
        if (!Objects.equals(this.dtMarcacao, other.dtMarcacao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "Marcacao{" + "dtMarcacao=" + dtMarcacao + '}';
        return (new SimpleDateFormat("dd/MM/yyyy").format(this.dtMarcacao));
    }

}
