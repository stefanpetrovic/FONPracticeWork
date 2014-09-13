/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import businessLogic.Controller;
import dao.domain.core.Title;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author stefan
 */
@FacesConverter("titleConverter")
public class TitleConverter implements Converter{

    private List<Title> titles;
    
    public TitleConverter() {
        titles = Controller.getInstance().getTitles();
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            long titleID = Long.parseLong(string);
            for (Title t : titles) {
                if (t.getTitleID() == titleID) {
                    return t;
                }
            }
            return null;
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Title) o).getTitleID().toString();
    }
    
}
