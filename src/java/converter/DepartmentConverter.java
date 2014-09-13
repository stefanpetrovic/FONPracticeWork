/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import businessLogic.Controller;
import dao.domain.core.Department;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author stefan
 */
@FacesConverter("departmentConverter")
public class DepartmentConverter implements Converter{

    private List<Department> departments;
    
    public DepartmentConverter() {
        departments = Controller.getInstance().getDepartments();
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            long departmentID = Long.parseLong(string);
            for (Department d : departments) {
                if (d.getDepartmentID() == departmentID) {
                    return d;
                }
            }
        }catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Department) o).getDepartmentID().toString();
    }
    
}
