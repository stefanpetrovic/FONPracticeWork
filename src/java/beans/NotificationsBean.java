/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import businessLogic.Controller;
import dao.domain.core.Communication;
import dao.domain.core.Employee;
import dao.domain.core.Student;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author stefan
 */
@ManagedBean
public class NotificationsBean {

    @ManagedProperty(value = "#{loggedInUserBean}")
    private LoggedInUserBean user;

    private int unreadCommunications;
    private int newThesisRequests;

    public LoggedInUserBean getUser() {
        return user;
    }

    @PostConstruct
    public void init() {
        List<Communication> communications = new ArrayList<>();;
        List<Work> unapprovedThesis = new ArrayList<>();
        if (!user.getLoggedInPerson().isEmpty()) {
            try {
                communications = Controller.getInstance().getCommunicationsWithUnreadMessages(user.getLoggedInPerson().get(user.getPersonIdentifier()));
                if (user.getPersonIdentifier() == Employee.class) unapprovedThesis = Controller.getInstance().getUnaprovedTheses(user.getLoggedInPerson().get(user.getPersonIdentifier()));
            } catch (EngineDAOException ex) {
                Logger.getLogger(NotificationsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        newThesisRequests = unapprovedThesis.size();
        unreadCommunications = communications.size();
    }

    public void setUser(LoggedInUserBean user) {
        this.user = user;
    }

    public int getUnreadCommunications() {
        return unreadCommunications;
    }

    public void setUnreadCommunications(int unreadCommunications) {
        this.unreadCommunications = unreadCommunications;
    }

    public int getNewThesisRequests() {
        return newThesisRequests;
    }

    public void setNewThesisRequests(int newThesisRequests) {
        this.newThesisRequests = newThesisRequests;
    }

}
