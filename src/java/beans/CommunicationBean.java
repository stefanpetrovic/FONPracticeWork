/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import businessLogic.Controller;
import dao.domain.core.Communication;
import dao.domain.core.Message;
import dao.exception.EngineDAOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author stefan
 */
@ManagedBean
public class CommunicationBean {
    
    private Long id;
    private Communication communication;
    private Message newMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        System.out.println(id);
        loadCommunication();
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
    
    public void loadCommunication(){
        try {
            communication = Controller.getInstance().getCommunicationByID(id);
        } catch (EngineDAOException ex) {
            Logger.getLogger(CommunicationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
