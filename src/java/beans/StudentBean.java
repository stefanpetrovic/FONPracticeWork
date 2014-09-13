/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import businessLogic.Controller;
import dao.domain.core.Student;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author stefan
 */
@ManagedBean
public class StudentBean {
    
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    public String addStudent() {
        Controller.getInstance().addStudent(student);
        // add message about successfull save
        return null;
    }
}
