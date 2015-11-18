/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.Marcacao;

/**
 *
 * @author Cristiano
 */
@ManagedBean
@RequestScoped
public class MarcacaoMB {

    private Marcacao marcacao;

    /**
     * Creates a new instance of MarcacaoMB
     */
    public MarcacaoMB() {
        try {
            marcacao = (new dao.MarcacaoDao()).BuscarMarcacaoDataAtual();
        } catch (Exception e) {
            marcacao = new Marcacao();
            marcacao.setDtMarcacao(new Date());
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Não foi possível salvar a marcação.",
                    e.getMessage(),
                    "mensagemMarcacao");
        }
    }

    public Marcacao getMarcacao() {
        return marcacao;
    }

    public void setMarcacao(Marcacao marcacao) {
        this.marcacao = marcacao;
    }

    public String salvar() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        SessaoSistemaMB sessaoSistemaMB = (SessaoSistemaMB) req.getSession().getAttribute("sessaoSistemaMB");

        try {
            //Atribui a marcação ao usuário corrente
            this.marcacao.setUsuario(sessaoSistemaMB.getUsuario());

            //objeto enviado via post
            (new dao.MarcacaoDao()).salvar(marcacao);

            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_INFO,
                    "Marcação [" + this.marcacao.toString() + "] salva!",
                    "",
                    "mensagemMarcacao");

            return "/faces/sistema/marcacao";
        } catch (Exception e) {
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Não foi possível salvar a marcação.",
                    e.getMessage(),
                    "mensagemMarcacao");
            return "/faces/sistema/marcacao";
        }
    }

}
