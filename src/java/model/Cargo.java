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
public class Cargo {

    private int id;
    private String nomeCargo;

    public Cargo(int id, String nomeCargo) {
        this.id = id;
        this.nomeCargo = nomeCargo;
    }

    public Cargo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cargo other = (Cargo) obj;
        return Objects.equals(this.nomeCargo, other.nomeCargo);
    }

    @Override
    public String toString() {
        //return id + " - " + nomeCargo;
        return nomeCargo;
    }
}
