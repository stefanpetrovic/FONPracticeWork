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
import dao.exception.EngineDAOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author stefan
 */
@ManagedBean
public class CommunicationBean {
    
    
    private Long id = new Long(1);
    
    private Communication communication;
    private Message newMessage;

    public CommunicationBean() {
//        loadCommunication();
//       prepareMessage();
    }
    
    @PostConstruct
    public void init(){
        loadCommunication(id);
        prepareMessage();
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
       // loadCommunication(id);
        
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
    
    public String sendMessage(){
         try { 
//            Controller.getInstance().addThesisRequest(work);
           
            Controller.getInstance().createMessage(newMessage);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Poruka uspešno poslata."));
        } catch (EngineDAOException ex) {
            Logger.getLogger(ThesisRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Nije moguće poslati poruku."));
        }
        return null;
    }
    
    public void prepareMessage(){
       newMessage = new Message();
        newMessage.setText("");
        newMessage.setRead(false);
        newMessage.setReciever(communication.getStudent().getPerson());
        newMessage.setSender(communication.getEmployee().getPerson());
      //  newMessage.setFileURI("");
        newMessage.setCommunication(communication);
       
    }
    
    
}
