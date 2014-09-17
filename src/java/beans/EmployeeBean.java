/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import businessLogic.Controller;
import dao.domain.core.Employee;
import dao.domain.core.EmployeeSubject;
import dao.domain.core.Person;
import dao.domain.core.Subject;
import dao.exception.EngineDAOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import validation.EmailValidator;
import validation.UsernameValidator;

/**
 *
 * @author stefan
 */
@ManagedBean
public class EmployeeBean {
    
    private Employee employee;
    private List<Subject> selectedSubjects;
    
    public EmployeeBean() {
        employee = new Employee();
        employee.setPerson(new Person());
        selectedSubjects = new ArrayList<>();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Subject> getSelectedSubjects() {
        return selectedSubjects;
    }

    public void setSelectedSubjects(List<Subject> selectedSubjects) {
        this.selectedSubjects = selectedSubjects;
    }
    
    public void validateEmail(FacesContext context, UIComponent componentToValidate, Object value) throws ValidatorException {
        EmailValidator ev = new EmailValidator();
        ev.setEmail(value.toString());
        if (!ev.isValid()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Email is not valid.");
            throw new ValidatorException(message);
        }
    }
    
    public void validateUsername(FacesContext context, UIComponent componentToValidate, Object value) throws ValidatorException{
        UsernameValidator uv = new UsernameValidator();
        uv.setUsername(value.toString());
        if (!uv.isValid()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Username is not valid.");
            throw new ValidatorException(message);
        }
    }
    
    public String addEmployee() {
        employee.getPerson().setPictureURI("");
        List<EmployeeSubject> subjects = employee.getEmployeeSubjectList();
        if (subjects == null) subjects = new ArrayList<EmployeeSubject>();
        for(Subject s : selectedSubjects) {
            EmployeeSubject es = new EmployeeSubject();
            es.setEmployee(employee);
            es.setSubject(s);
            subjects.add(es);
        }
        try {
            Controller.getInstance().addEmployee(employee);
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Zaposleni je uspesno sacuvan"));
            employee = new Employee();
            employee.setPerson(new Person());
        } catch (EngineDAOException ex) {
            Logger.getLogger(EmployeeBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Greska prilikom cuvanja zaposlenog"));
        }
        return null;
    }
}
