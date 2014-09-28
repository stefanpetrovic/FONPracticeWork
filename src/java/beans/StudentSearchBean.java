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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author stefan
 */
@ManagedBean
public class StudentSearchBean {
    
    private Student existingStudent;
    private List<Student> foundStudents;

    public StudentSearchBean() {
        existingStudent = new Student();
        existingStudent.setPerson(new Person());
    }
    
    public Student getExistingStudent() {
        return existingStudent;
    }

    public void setExistingStudent(Student existingStudent) {
        this.existingStudent = existingStudent;
    }
    
    public List<Student> getFoundStudents() {
        return foundStudents;
    }

    public void setFoundStudents(List<Student> foundStudents) {
        this.foundStudents = foundStudents;
    }
    
    public void searchForStudent() {
        try {
            if (existingStudent.getPerson().getName().equals("")) existingStudent.getPerson().setName(null);
            if (existingStudent.getPerson().getSurname().equals("")) existingStudent.getPerson().setSurname(null);
            foundStudents = Controller.getInstance().getStudents(existingStudent);
            if (foundStudents.size() > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Sistem je pronasao studente."));
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Sistem nije pronasao studente."));
            }
        } catch (EngineDAOException ex) {
            Logger.getLogger(StudentSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            foundStudents = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Sistem nije pronasao studente."));
        }
    }

}
