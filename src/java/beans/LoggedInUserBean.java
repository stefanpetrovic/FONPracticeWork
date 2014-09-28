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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author stefan
 */
@ManagedBean
@SessionScoped
public class LoggedInUserBean implements Serializable {

    private String username;
    private String password;
    private Class personIdentifier;
    private HashMap<Class, Person> loggedInPerson = new HashMap<Class, Person>();

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

    public Class getPersonIdentifier() {
        return personIdentifier;
    }

    public void setPersonIdentifier(Class personIdentifier) {
        this.personIdentifier = personIdentifier;
    }

    public HashMap<Class, Person> getLoggedInPerson() {
        return loggedInPerson;
    }

    public void setLoggedInPerson(HashMap<Class, Person> loggedInPerson) {
        this.loggedInPerson = loggedInPerson;
    }

    //ova metoda je za brisanje
    public void uploadImage() {
    }

    public void updateUser() {
        try {
            Controller.getInstance().updatePerson(loggedInPerson.get(personIdentifier));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Korisnik uspesno izmenjen."));
        } catch (EngineDAOException ex) {
            Logger.getLogger(LoggedInUserBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Greska prilikom izmene."));
        }

    }

    public String login() {
        try {
            String message = "";
            Person person = Controller.getInstance().login(username, password);
            if (person.getStudent() != null) {
                personIdentifier = person.getStudent().getClass();
            } else if (person.getEmployee() != null){
                personIdentifier = person.getEmployee().getClass();
            }else {
                personIdentifier = person.getSuperAdminList().get(0).getClass();
            }
            message = "Uspešno ste se ulogovali " + person.getName() + ".";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            loggedInPerson.put(personIdentifier, person);
            return "first-page";
        } catch (EngineDAOException ex) {
            Logger.getLogger(LoggedInUserBean.class.getName()).log(Level.SEVERE, null, ex);
            String message = "Neuspelo prijavljivanje!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));

            return null;
        }
    }

    public String logout() {
        personIdentifier = null;
        username = password = null;
        loggedInPerson.clear();
        System.out.println("LOGOUT");
        return "index";
    }

    //returns name of menu file to include in template
    public String returnMenu() {
        if (personIdentifier == Student.class) {
            return "student-menu";
        } else if (personIdentifier == Employee.class) {
            return "professor-menu";
        }
        return "default";
    }
}
