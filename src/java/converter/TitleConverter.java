/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import businessLogic.Controller;
import dao.domain.core.Title;
import dao.exception.EngineDAOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            titles = Controller.getInstance().getTitles();
        } catch (EngineDAOException ex) {
            Logger.getLogger(TitleConverter.class.getName()).log(Level.SEVERE, null, ex);
            titles = new ArrayList<>();
        }
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
