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
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        SessaoSistemaMB sessaoSistemaMB = (SessaoSistemaMB) req.getSession().getAttribute("sessaoSistemaMB");

        try {
            marcacao = (new dao.MarcacaoDao()).BuscarMarcacaoDataAtual(sessaoSistemaMB.getUsuario());
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

            //Verifica se a data de entrada é maior que a data de saída
            if (util.Util.isDataMaior(this.marcacao.getDtEntrada(), this.marcacao.getDtSaida())) {
                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_WARN,
                        "Verifique o período marcado: a data de entrada não pode ser maior ou igual a data de saída!",
                        "",
                        "mensagemMarcacao");
                return "/faces/sistema/marcacao";
            }

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
