/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import businessLogic.Controller;
import dao.domain.core.Course;
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
@FacesConverter("courseConverter")
public class CourseConverter implements Converter{

    private List<Course> courses;
    
    public CourseConverter() {
        try {
            courses = Controller.getInstance().getCourses();
        } catch (EngineDAOException ex) {
            Logger.getLogger(CourseConverter.class.getName()).log(Level.SEVERE, null, ex);
            courses = new ArrayList<>();
        }
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            long cid = Long.parseLong(string);
            for (Course c : courses) {
                if (c.getCourseID() == cid) {
                    return c;
                }
            }            
        }catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Course) o).getCourseID().toString();
    }
    
}
