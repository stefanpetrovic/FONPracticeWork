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
@Table(name = "title")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Title.findAll", query = "SELECT t FROM Title t"),
    @NamedQuery(name = "Title.findByTitleID", query = "SELECT t FROM Title t WHERE t.titleID = :titleID"),
    @NamedQuery(name = "Title.findByName", query = "SELECT t FROM Title t WHERE t.name = :name")})
public class Title implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "titleID")
    private Long titleID;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "title")
    private List<Employee> employeeList;

    public Title() {
    }

    public Title(Long titleID) {
        this.titleID = titleID;
    }

    public Title(Long titleID, String name) {
        this.titleID = titleID;
        this.name = name;
    }

    public Long getTitleID() {
        return titleID;
    }

    public void setTitleID(Long titleID) {
        this.titleID = titleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (titleID != null ? titleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Title)) {
            return false;
        }
        Title other = (Title) object;
        if ((this.titleID == null && other.titleID != null) || (this.titleID != null && !this.titleID.equals(other.titleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "dao.domain.core.Title[ titleID=" + titleID + " ]";
        return name;
    }
    
}
