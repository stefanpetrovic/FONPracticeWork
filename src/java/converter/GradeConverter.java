/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;


import businessLogic.Controller;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author MIRA
 */
@FacesConverter("gradesConverter")
public class GradeConverter implements Converter{
    
    private List<Integer> grades;

    public GradeConverter() {
        grades = Controller.getInstance().createGrades();
    }
    
    

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return Integer.parseInt(string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Integer)o).toString();
    }
    
}
