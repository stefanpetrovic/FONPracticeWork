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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Djole
 */
@Entity
@Table(name = "commision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commision.findAll", query = "SELECT c FROM Commision c"),
    @NamedQuery(name = "Commision.findByCommisionID", query = "SELECT c FROM Commision c WHERE c.commisionID = :commisionID")})
public class Commision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "commisionID")
    private Long commisionID;
    @OneToMany(mappedBy = "commision")
    private List<Work> workList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commisionID")
    private List<CommisionMember> commisionMemberList;

    public Commision() {
    }

    public Commision(Long commisionID) {
        this.commisionID = commisionID;
    }

    public Long getCommisionID() {
        return commisionID;
    }

    public void setCommisionID(Long commisionID) {
        this.commisionID = commisionID;
    }

    @XmlTransient
    public List<Work> getWorkList() {
        return workList;
    }

    public void setWorkList(List<Work> workList) {
        this.workList = workList;
    }

    @XmlTransient
    public List<CommisionMember> getCommisionMemberList() {
        return commisionMemberList;
    }

    public void setCommisionMemberList(List<CommisionMember> commisionMemberList) {
        this.commisionMemberList = commisionMemberList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commisionID != null ? commisionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commision)) {
            return false;
        }
        Commision other = (Commision) object;
        if ((this.commisionID == null && other.commisionID != null) || (this.commisionID != null && !this.commisionID.equals(other.commisionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.domain.core.Commision[ commisionID=" + commisionID + " ]";
    }
    
}
