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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
    @NamedQuery(name = "SuperAdmin.findBySuperadminID", query = "SELECT s FROM SuperAdmin s WHERE s.superadminID = :superadminID")})
public class SuperAdmin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "super_adminID")
    private Long superadminID;
    @JoinColumn(name = "super_adminID", referencedColumnName = "personID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public SuperAdmin() {
    }

    public SuperAdmin(Long superadminID) {
        this.superadminID = superadminID;
    }

    public Long getSuperadminID() {
        return superadminID;
    }

    public void setSuperadminID(Long superadminID) {
        this.superadminID = superadminID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (superadminID != null ? superadminID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuperAdmin)) {
            return false;
        }
        SuperAdmin other = (SuperAdmin) object;
        if ((this.superadminID == null && other.superadminID != null) || (this.superadminID != null && !this.superadminID.equals(other.superadminID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.domain.core.SuperAdmin[ superadminID=" + superadminID + " ]";
    }
    
}
