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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author stefan
 */
@ManagedBean
@ViewScoped
public class ThesisRequestBean {
    
    private Work work;
    private List<Employee> professors;
    private List<Subject> subjects;
    
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
        subjects = new ArrayList<>();
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
    
    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
    
    public void onProfessorChange() {
        if (work.getMentor() != null && !work.getMentor().equals("")) {
            try {
                subjects = Controller.getInstance().getAllSubjectsByProfessor(work.getMentor());
            } catch (EngineDAOException ex) {
                Logger.getLogger(ThesisRequestBean.class.getName()).log(Level.SEVERE, null, ex);
                subjects = new ArrayList<>();
            }
        }else {
            subjects = new ArrayList<>();
        }
    }
    
    public String sendRequest() {
        try { 
            Controller.getInstance().addThesisRequest(work);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Zahtev je uspešno poslat."));
        } catch (EngineDAOException ex) {
            Logger.getLogger(ThesisRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Greška prilikom slanja zahteva."));
        }
        return null;
    }

}
