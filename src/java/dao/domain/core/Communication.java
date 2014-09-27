/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.domain.core;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Djole
 */
@Entity
@Table(name = "communication")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Communication.findAll", query = "SELECT c FROM Communication c"),
    @NamedQuery(name = "Communication.findByCommunicationID", query = "SELECT c FROM Communication c WHERE c.communicationID = :communicationID"),
    @NamedQuery(name = "Communication.findByLocked", query = "SELECT c FROM Communication c WHERE c.locked = :locked")})
public class Communication implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "communicationID")
    private Long communicationID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "locked")
    private boolean locked;
    @JoinColumn(name = "employee", referencedColumnName = "employeeID")
    @ManyToOne(optional = false)
    private Employee employee;
    @JoinColumn(name = "student", referencedColumnName = "studentID")
    @ManyToOne(optional = false)
    private Student student;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "communication")
    private List<Message> messageList;

    public Communication() {
    }

    public Communication(Long communicationID) {
        this.communicationID = communicationID;
    }

    public Communication(Long communicationID, boolean locked) {
        this.communicationID = communicationID;
        this.locked = locked;
    }

    public Long getCommunicationID() {
        return communicationID;
    }

    public void setCommunicationID(Long communicationID) {
        this.communicationID = communicationID;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (communicationID != null ? communicationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Communication)) {
            return false;
        }
        Communication other = (Communication) object;
        if ((this.communicationID == null && other.communicationID != null) || (this.communicationID != null && !this.communicationID.equals(other.communicationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.domain.core.Communication[ communicationID=" + communicationID + " ]";
    }
    
}
