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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Djole
 */
@Entity
@Table(name = "super_admin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SuperAdmin.findAll", query = "SELECT s FROM SuperAdmin s"),
    @NamedQuery(name = "SuperAdmin.findByAdminID", query = "SELECT s FROM SuperAdmin s WHERE s.adminID = :adminID"),
    @NamedQuery(name = "SuperAdmin.findByPerson", query = "SELECT s FROM SuperAdmin s WHERE s.person = :person")})
public class SuperAdmin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "adminID")
    private Long adminID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "person")
    private long person;

    public SuperAdmin() {
    }

    public SuperAdmin(Long adminID) {
        this.adminID = adminID;
    }

    public SuperAdmin(Long adminID, long person) {
        this.adminID = adminID;
        this.person = person;
    }

    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }

    public long getPerson() {
        return person;
    }

    public void setPerson(long person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminID != null ? adminID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuperAdmin)) {
            return false;
        }
        SuperAdmin other = (SuperAdmin) object;
        if ((this.adminID == null && other.adminID != null) || (this.adminID != null && !this.adminID.equals(other.adminID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.domain.core.SuperAdmin[ adminID=" + adminID + " ]";
    }
    
}
