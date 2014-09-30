/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.domain.core;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Djole
 */
@Entity
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByMessageID", query = "SELECT m FROM Message m WHERE m.messageID = :messageID"),
    @NamedQuery(name = "Message.findByFileURI", query = "SELECT m FROM Message m WHERE m.fileURI = :fileURI"),
    @NamedQuery(name = "Message.findByIsRead", query = "SELECT m FROM Message m WHERE m.isRead = :isRead")})
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "messageID")
    private Long messageID;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "text")
    private String text;
    @Size(max = 255)
    @Column(name = "fileURI")
    private String fileURI;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isRead")
    private boolean isRead;
    @JoinColumn(name = "communication", referencedColumnName = "communicationID")
    @ManyToOne(optional = false)
    private Communication communication;
    @JoinColumn(name = "reciever", referencedColumnName = "personID")
    @ManyToOne(optional = false)
    private Person reciever;
    @JoinColumn(name = "sender", referencedColumnName = "personID")
    @ManyToOne(optional = false)
    private Person sender;

    public Message() {
    }

    public Message(Long messageID) {
        this.messageID = messageID;
    }

    public Message(Long messageID, String text, boolean isRead) {
        this.messageID = messageID;
        this.text = text;
        this.isRead = isRead;
    }

    public Long getMessageID() {
        return messageID;
    }

    public void setMessageID(Long messageID) {
        this.messageID = messageID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFileURI() {
        return fileURI;
    }

    public void setFileURI(String fileURI) {
        this.fileURI = fileURI;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Communication getCommunication() {
        return communication;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }

    public Person getReciever() {
        return reciever;
    }

    public void setReciever(Person reciever) {
        this.reciever = reciever;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageID != null ? messageID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.messageID == null && other.messageID != null) || (this.messageID != null && !this.messageID.equals(other.messageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.domain.core.Message[ messageID=" + messageID + " ]";
    }
    
}
