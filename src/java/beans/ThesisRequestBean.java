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
import dao.exception.EngineDAOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @ManagedProperty(value = "#{loggedInUserBean}")
    private LoggedInUserBean loggedInUserBean;
    
    @PostConstruct
    public void init() {
        work = new Work();
        Person loggedInPerson = loggedInUserBean.getLoggedInPerson().get(loggedInUserBean.getPersonIdentifier());
        work.setStudent(loggedInPerson.getStudent());
        try {
            professors = Controller.getInstance().getAllProfessors();
        } catch (EngineDAOException ex) {
            Logger.getLogger(ThesisRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            professors = new ArrayList<>();
        }
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

    public LoggedInUserBean getLoggedInUserBean() {
        return loggedInUserBean;
    }

    public void setLoggedInUserBean(LoggedInUserBean loggedInUserBean) {
        this.loggedInUserBean = loggedInUserBean;
    }
    
    public String sendRequest() {
        try { 
            Controller.getInstance().addThesisRequest(work);
        } catch (EngineDAOException ex) {
            Logger.getLogger(ThesisRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
