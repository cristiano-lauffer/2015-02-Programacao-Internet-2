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
public enum TipoMarcacao {

    MARCACAO_PONTO (1),
    MARCACAO_WEB (2),
    MARCACAO_EDICAO (3),
    MARCACAO_FERIAS (4);
    private int tipoMarcacao;
    
    TipoMarcacao(int tipoMarcacao){
        this.tipoMarcacao = tipoMarcacao;
    }
    
    public int getValue(){
        return tipoMarcacao;
    }
    
}
