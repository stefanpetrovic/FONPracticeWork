/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import businessLogic.Controller;
import dao.domain.core.Communication;
import dao.domain.core.Person;
import java.util.ArrayList;
import java.util.List;
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
public class ListCommunicationsBean {

    List<Communication> communications;

    @ManagedProperty(value = "#{loggedInUserBean}")
    private LoggedInUserBean loggedInUserBean;

    @PostConstruct
    public void init() {
        Person loggedInPerson = loggedInUserBean.getLoggedInPerson().get(loggedInUserBean.getPersonIdentifier());
        try {

            communications = new ArrayList<>();
            if (loggedInPerson.getStudent() != null) {
                communications = Controller.getInstance().getCommunicationsByStudent(loggedInPerson.getStudent());
            }
            if (loggedInPerson.getEmployee() != null) {
                communications = Controller.getInstance().getCommunicationsByEmployee(loggedInPerson.getEmployee());
            }

            //System.out.println("ddsf " + theses.size());

            if (communications.size() > 0) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Dobijeni radovi."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nemate komunikacija.", null));

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška prilikom čitanja komunikacija.", null));

        }
    }
    

    public ListCommunicationsBean() {

    }

    public List<Communication> getCommunications() {
        return communications;
    }

    public void setCommunications(List<Communication> communications) {
        this.communications = communications;
    }

}
