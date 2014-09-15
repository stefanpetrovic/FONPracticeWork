/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import businessLogic.Controller;
import dao.domain.core.Person;
import dao.domain.core.Student;
import dao.exception.EngineDAOException;
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
public class StudentBean {
    
    private Student student;
    
    public StudentBean() {
        student = new Student();
        student.setPerson(new Person());
    }
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    public void validateUsername(FacesContext context, UIComponent componentToValidate, Object value) throws ValidatorException{
        UsernameValidator uv = new UsernameValidator();
        uv.setUsername(student.getPerson().getUsername());
        if (!uv.isValid()) {
            FacesMessage message = new FacesMessage("Email is not valid.");
            throw new ValidatorException(message);
        }
    }
    
    public void validateEmail(FacesContext context, UIComponent componentToValidate, Object value) throws ValidatorException {
        EmailValidator ev = new EmailValidator();
        ev.setEmail(student.getPerson().getEmail());
        if (!ev.isValid()) {
            FacesMessage message = new FacesMessage("Username is not valid.");
            throw new ValidatorException(message);
        }
    }
    
    public String addStudent() {
        student.getPerson().setPictureURI("");
        try {
            Controller.getInstance().addStudent(student);
            FacesContext.getCurrentInstance().addMessage("infoMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Student uspesno sacuvan."));
            student = new Student();
            student.setPerson(new Person());
        } catch (EngineDAOException ex) {
            Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Greska prilikom cuvanja studenta."));
        }
        
        // add message about successfull save
        return null;
    }
}
