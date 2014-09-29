/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import businessLogic.Controller;
import dao.domain.core.Employee;
import dao.domain.core.Person;
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
 * @author MIRA
 */
@ManagedBean
public class ThesesForEmployee {

    private List<Work> theses;

    @ManagedProperty(value = "#{loggedInUserBean}")
    private LoggedInUserBean loggedInUserBean;

    @PostConstruct
    public void init() {
        Person loggedInPerson = loggedInUserBean.getLoggedInPerson().get(loggedInUserBean.getPersonIdentifier());
        try {
            theses = Controller.getInstance().getPersonWorks(loggedInPerson);
            System.out.println("ddsf "+theses.size());
            if (theses.size() > 0) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Dobijeni radovi."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nemate radova.", null));

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška prilikom čitanja radova.", null));

        }

//        work = new Work();
//        Person loggedInPerson = loggedInUserBean.getLoggedInPerson().get(loggedInUserBean.getPersonIdentifier());
//        work.setStudent(loggedInPerson.getStudent());
//        try {
//            professors = Controller.getInstance().getAllProfessors();
//            returnAll(loggedInPerson);
//        } catch (EngineDAOException ex) {
//            Logger.getLogger(ThesisRequestBean.class.getName()).log(Level.SEVERE, null, ex);
//            theses = new ArrayList<>();
//        }
        //subjects = new ArrayList<>();
    }

    public LoggedInUserBean getLoggedInUserBean() {
        return loggedInUserBean;
    }

    public void setLoggedInUserBean(LoggedInUserBean loggedInUserBean) {
        this.loggedInUserBean = loggedInUserBean;
    }

    public List<Work> getTheses() {
        return theses;
    }

    public void setTheses(List<Work> theses) {
        this.theses = theses;
    }

    public ThesesForEmployee() {
        theses = new ArrayList<>();

    }

    public String returnAll(Person person) {
        try {
            theses = Controller.getInstance().getPersonWorks(person);

            if (theses.size() > 0) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dobijeni radovi.", null));
                return null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nemate radova.", null));
                return null;
            }
        } catch (EngineDAOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška prilikom čitanja radova.", null));

        }
        return null;
    }

    public String getUngradedTheses() {
        try {
            theses = Controller.getInstance().getUngradedTheses(loggedInUserBean.getLoggedInPerson().get(loggedInUserBean.getPersonIdentifier()));
            if (theses.size() > 0) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dobijeni radovi.", null));
                return null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nemate radova.", null));
                return null;
            }
        } catch (EngineDAOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška prilikom čitanja radova.", null));

        }
        return null;
    }

    public String getUncomissionedTheses() {
        try {
            theses = Controller.getInstance().getUncomissionedTheses(loggedInUserBean.getLoggedInPerson().get(loggedInUserBean.getPersonIdentifier()));
            if (theses.size() > 0) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dobijeni radovi.", null));
                return null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nemate radova.", null));
                return null;
            }
        } catch (EngineDAOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška prilikom čitanja radova.", null));

        }
        return null;
    }

    public String getUnaprovedTheses() {
        try {
            theses = Controller.getInstance().getUnaprovedTheses(loggedInUserBean.getLoggedInPerson().get(loggedInUserBean.getPersonIdentifier()));
            if (theses.size() > 0) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dobijeni radovi.", null));
                return null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nemate radova.", null));
                return null;
            }
        } catch (EngineDAOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška prilikom čitanja radova.", null));

        }
        return null;
    }

}
