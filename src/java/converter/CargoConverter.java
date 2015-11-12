
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import bean.UsuarioMB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Cargo;

/**
 *
 * @author Cristiano
 */
@FacesConverter(value = "cargoConverter")
public class CargoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        UsuarioMB usuarioMB = context.getApplication()
                .evaluateExpressionGet(context, "#{usuarioMB}",
                        UsuarioMB.class);
        Cargo cargo = usuarioMB.findCargoByNome(value);
        return cargo;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String string = null;
        if (value instanceof Cargo) {
            string = ((Cargo) value).getNomeCargo();
        }
        return string;
    }
    
}
