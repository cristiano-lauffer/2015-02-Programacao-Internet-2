/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlSelectOneMenu;
import model.Cargo;

/**
 *
 * @author Cristiano
 */
@ManagedBean
@RequestScoped
public class CargoMB {

    private Cargo cargo;
    private HtmlDataTable dataTableCargos;
    private HtmlSelectOneMenu selectOneMenuCargos;

    /**
     * Creates a new instance of CatgoMB
     */
    public CargoMB() {
        cargo = new Cargo();
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public HtmlDataTable getDataTableCargos() {
        return dataTableCargos;
    }

    public void setDataTableCargos(HtmlDataTable dataTableCargos) {
        this.dataTableCargos = dataTableCargos;
    }
    
    public HtmlSelectOneMenu getSelectOneMenuCargos() {
        return selectOneMenuCargos;
    }

    public void setSelectOneMenuCargos(HtmlSelectOneMenu selectOneMenuCargos) {
        this.selectOneMenuCargos = selectOneMenuCargos;
    }

    public List<Cargo> getArrayList() throws Exception {
        return (new dao.CargoDao()).getArrayListCargos();
    }
    
    public List<Object[]> getArrayListSum() throws Exception {
        return (new dao.CargoDao()).getArrayListCargosSum();
    }

    public void remover() {
        try {
            boolean blnRetorno = (new dao.CargoDao()).remover(this.cargo);
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_INFO,
                    "Cargo removido!",
                    "",
                    "mensagemCargos");
        } catch (Exception e) {
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Não foi possível excluir o cargo.",
                    e.getMessage(),
                    "mensagemCargos");
        }
    }

    public String editar() {
        Cargo objCargo;
        try {

            //Verifica se o nome foi cadastrado para outro cargo
            objCargo = (new dao.CargoDao()).buscar(cargo.getNomeCargo().trim(), cargo.getId());

            if (objCargo != null) {

                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_WARN,
                        "Nome cadastrado para outro cargo![" + objCargo.toString() + "]",
                        "",
                        "mensagemCargos");
                return "/faces/sistema/admin/cargo_editar";

            }

            //objeto enviado via post
            (new dao.CargoDao()).salvar(cargo);

            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_INFO,
                    "Cargo [" + this.cargo.toString() + "] editado!",
                    "",
                    "mensagemCargos");

            return "/faces/sistema/admin/cargos";
        } catch (Exception e) {
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Não foi possível editar o cargo.",
                    e.getMessage(),
                    "mensagemCargos");
            return "/faces/sistema/admin/cargo_novo";
        }
    }

    public String inserir() {
        Cargo objCargo;
        try {

            //Verifica se o nome foi cadastrado para outro cargo
            objCargo = (new dao.CargoDao()).buscar(cargo.getNomeCargo().trim());

            if (objCargo != null) {

                util.Util.FacesContextAddMessage(
                        FacesMessage.SEVERITY_WARN,
                        "Nome cadastrado para outro cargo!(" + objCargo.toString() + ")",
                        "",
                        "mensagemCargos");
                return "/faces/sistema/admin/cargo_novo";

            }

            //objeto enviado via post
            (new dao.CargoDao()).salvar(cargo);

            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_INFO,
                    "Cargo [" + this.cargo.toString() + "] cadastrado!",
                    "",
                    "mensagemCargos");

            return "/faces/sistema/admin/cargos";
        } catch (Exception e) {
            util.Util.FacesContextAddMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Não foi possível inserir o cargo.",
                    e.getMessage(),
                    "mensagemCargos");
            return "/faces/sistema/admin/cargo_novo";
        }
    }

}
