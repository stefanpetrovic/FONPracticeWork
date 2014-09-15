/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import dao.domain.core.Employee;
import dao.domain.core.Person;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author stefan
 */
@ManagedBean
public class EmployeeBean {
    
    private Employee employee;
    
    public EmployeeBean() {
        employee = new Employee();
        employee.setPerson(new Person());
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public String addEmployee() {
        return null;
    }
}
