/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import businessLogic.Controller;
import dao.domain.core.Person;
import dao.domain.core.Student;
import java.util.List;
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
        foundStudents = Controller.getInstance().getStudents(existingStudent);
    }

}
