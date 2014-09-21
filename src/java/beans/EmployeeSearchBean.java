/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import businessLogic.Controller;
import dao.domain.core.Employee;
import dao.domain.core.Person;
import dao.domain.core.Title;
import dao.exception.EngineDAOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author stefan
 */
@ManagedBean
@ViewScoped
public class EmployeeSearchBean {
    
    private Employee existingEmployee;
    private List<Employee> foundEmployees;

    public EmployeeSearchBean() {
        existingEmployee = new Employee();
        existingEmployee.setPerson(new Person());
        foundEmployees = new ArrayList<>();
    }
    
    public Employee getExistingEmployee() {
        return existingEmployee;
    }

    public void setExistingEmployee(Employee existingEmployee) {
        this.existingEmployee = existingEmployee;
    }

    public List<Employee> getFoundEmployees() {
        return foundEmployees;
    }

    public void setFoundEmployees(List<Employee> foundEmployees) {
        this.foundEmployees = foundEmployees;
    }
    
    public void findEmployees() {
        try {
            if (existingEmployee.getPerson().getName().equals("")) existingEmployee.getPerson().setName(null);
            if (existingEmployee.getPerson().getSurname().equals("")) existingEmployee.getPerson().setSurname(null);
            foundEmployees = Controller.getInstance().getEmployees(existingEmployee);
        } catch (EngineDAOException ex) {
            Logger.getLogger(EmployeeSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            foundEmployees = new ArrayList<>();
        }
    }
    
}
