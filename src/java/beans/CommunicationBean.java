/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import businessLogic.Controller;
import dao.domain.core.Communication;
import dao.domain.core.Employee;
import dao.domain.core.Message;
import dao.domain.core.Person;
import dao.exception.EngineDAOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stefan
 */
@ManagedBean
@ViewScoped
public class CommunicationBean implements Serializable{
    
    
    private Long id;
    
    private Communication communication;
    private Message newMessage;
    
    @ManagedProperty(value="#{loggedInUserBean}")
    private LoggedInUserBean user;
    
    public CommunicationBean() {
//        loadCommunication();
//       prepareMessage();
    }
    
    @PostConstruct
    public void init(){
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        id = Long.parseLong(req.getParameter("id"));
        loadCommunication(id);
        prepareMessage();
    }

    public LoggedInUserBean getUser() {
        return user;
    }

    public void setUser(LoggedInUserBean user) {
        this.user = user;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;        
    }

    public Communication getCommunication() {
        return communication;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }

    public Message getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(Message newMessage) {
        this.newMessage = newMessage;
    }
    
    public boolean isSenderEmployee(Message message) {
        if (message.getSender().getEmployee() instanceof Employee) return true;
        return false;
    }
    
    public void loadCommunication(Long id){
        try {
            communication = Controller.getInstance().getCommunicationByID(id);
        } catch (EngineDAOException ex) {
            Logger.getLogger(CommunicationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage(){
         try { 
//            Controller.getInstance().addThesisRequest(work);
           
            Controller.getInstance().createMessage(newMessage);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Poruka uspešno poslata."));
            communication.getMessageList().add(newMessage);
            prepareMessage();
        } catch (EngineDAOException ex) {
            Logger.getLogger(ThesisRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Nije moguće poslati poruku."));
        }
    }
    
    public void prepareMessage(){
       newMessage = new Message();
        newMessage.setText("");
        newMessage.setIsRead(false);
        Person sender = user.getLoggedInPerson().get(user.getPersonIdentifier());
        if (sender.getStudent() != null) {
            newMessage.setReciever(communication.getEmployee().getPerson());
        }else {
            newMessage.setReciever(communication.getStudent().getPerson());
        }
        newMessage.setSender(sender);
      //  newMessage.setFileURI("");
        newMessage.setCommunication(communication);
       
    }
    
    
}
