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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Djole
 */
@Entity
@Table(name = "commision_member")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommisionMember.findAll", query = "SELECT c FROM CommisionMember c"),
    @NamedQuery(name = "CommisionMember.findByCommisionmemberID", query = "SELECT c FROM CommisionMember c WHERE c.commisionmemberID = :commisionmemberID"),
    @NamedQuery(name = "CommisionMember.findByRole", query = "SELECT c FROM CommisionMember c WHERE c.role = :role")})
public class CommisionMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "commision_memberID")
    private Long commisionmemberID;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;
    @JoinColumn(name = "commisionID", referencedColumnName = "commisionID")
    @ManyToOne(optional = false)
    private Commision commisionID;
    @JoinColumn(name = "professor", referencedColumnName = "employeeID")
    @ManyToOne(optional = false)
    private Employee professor;

    public CommisionMember() {
    }

    public CommisionMember(Long commisionmemberID) {
        this.commisionmemberID = commisionmemberID;
    }

    public CommisionMember(Long commisionmemberID, String role) {
        this.commisionmemberID = commisionmemberID;
        this.role = role;
    }

    public Long getCommisionmemberID() {
        return commisionmemberID;
    }

    public void setCommisionmemberID(Long commisionmemberID) {
        this.commisionmemberID = commisionmemberID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Commision getCommisionID() {
        return commisionID;
    }

    public void setCommisionID(Commision commisionID) {
        this.commisionID = commisionID;
    }

    public Employee getProfessor() {
        return professor;
    }

    public void setProfessor(Employee professor) {
        this.professor = professor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commisionmemberID != null ? commisionmemberID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommisionMember)) {
            return false;
        }
        CommisionMember other = (CommisionMember) object;
        if ((this.commisionmemberID == null && other.commisionmemberID != null) || (this.commisionmemberID != null && !this.commisionmemberID.equals(other.commisionmemberID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.domain.core.CommisionMember[ commisionmemberID=" + commisionmemberID + " ]";
    }
    
}
