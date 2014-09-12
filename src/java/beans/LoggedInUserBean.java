/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import businessLogic.Controller;
import dao.domain.core.Employee;
import dao.domain.core.Person;
import dao.domain.core.Student;
import dao.exception.EngineDAOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author stefan
 */
@ManagedBean
@SessionScoped
public class LoggedInUserBean implements Serializable{
    
    private String username;
    private String password;
    private Employee loggedInEmployee;
    private Student loggedInStudent;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getLoggedInEmployee() {
        return loggedInEmployee;
    }

    public void setLoggedInEmployee(Employee loggedInEmployee) {
        this.loggedInEmployee = loggedInEmployee;
    }

    public Student getLoggedInStudent() {
        return loggedInStudent;
    }

    public void setLoggedInStudent(Student loggedInStudent) {
        this.loggedInStudent = loggedInStudent;
    }
    
    public String login() {
        try {
            Person loggedInPerson = Controller.getInstance().login(username, password);
            if (loggedInPerson.getStudent() != null) {
                loggedInStudent = loggedInPerson.getStudent();
            }else {
                loggedInEmployee = loggedInPerson.getEmployee();
            }
            return "first-page";
        } catch (EngineDAOException ex) {
            Logger.getLogger(LoggedInUserBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
