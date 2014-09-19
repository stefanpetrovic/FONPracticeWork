/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import businessLogic.Controller;
import dao.domain.core.Employee;
import dao.domain.core.Person;
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
@FacesConverter("employeeConverter")
public class EmployeeConverter implements Converter {

    private List<Employee> employees;

    public EmployeeConverter() {
        try {
            employees = Controller.getInstance().getAllProfessors();
        } catch (EngineDAOException ex) {
            Logger.getLogger(EmployeeConverter.class.getName()).log(Level.SEVERE, null, ex);
            employees = new ArrayList<>();
        }
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            long eid = Long.parseLong(string);
            for (Employee e : employees) {
                if (e.getEmployeeID() == eid) {
                    return e;
                }
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Employee) o).getEmployeeID().toString();
    }

}
