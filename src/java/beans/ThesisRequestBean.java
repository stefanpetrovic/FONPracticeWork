/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import businessLogic.Controller;
import dao.domain.core.Employee;
import dao.domain.core.Person;
import dao.domain.core.Subject;
import dao.domain.core.Work;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author stefan
 */
@ManagedBean
public class ThesisRequestBean {
    
    private Work work;
    private List<Employee> professors;
    
    @ManagedProperty(value="#{loggedInUserBean}")
    private LoggedInUserBean loggedInUser;
    
    @PostConstruct
    public void setup() {
        work = new Work();
        Person loggedInPerson = loggedInUser.getLoggedInPerson().get(loggedInUser.getPersonIdentifier());
        work.setStudent(loggedInPerson.getStudent());
        professors = Controller.getInstance().getAllProfessors();
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }
    
    public List<Employee> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Employee> professors) {
        this.professors = professors;
    }

    public LoggedInUserBean getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(LoggedInUserBean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    
    public String sendRequest() {
        Controller.getInstance().addThesisRequest(work); 
        return null;
    }
}
