/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import businessLogic.Controller;
import dao.domain.core.Subject;
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
@FacesConverter("subjectConverter")
public class SubjectConverter implements Converter{

    private List<Subject> subjects;
    
    public SubjectConverter() {
        try {
            subjects = Controller.getInstance().getSubjects();
        } catch (EngineDAOException ex) {
            subjects = new ArrayList<>();
            Logger.getLogger(SubjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            long sid = Long.parseLong(string);
            for (Subject s : subjects) {
                if (s.getSubjectID() == sid) {
                    return s;
                }
            }            
        }catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Subject) o).getSubjectID().toString();
    }
    
}
