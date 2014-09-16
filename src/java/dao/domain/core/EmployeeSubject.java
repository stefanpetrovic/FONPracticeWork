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
@Table(name = "employee_subject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeSubject.findAll", query = "SELECT e FROM EmployeeSubject e"),
    @NamedQuery(name = "EmployeeSubject.findByEmployesubjectID", query = "SELECT e FROM EmployeeSubject e WHERE e.employesubjectID = :employesubjectID")})
public class EmployeeSubject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "employe_subjectID")
    private Long employesubjectID;
    @JoinColumn(name = "employee", referencedColumnName = "employeeID")
    @ManyToOne(optional = false)
    private Employee employee;
    @JoinColumn(name = "subject", referencedColumnName = "subjectID")
    @ManyToOne(optional = false)
    private Subject subject;

    public EmployeeSubject() {
    }

    public EmployeeSubject(Long employesubjectID) {
        this.employesubjectID = employesubjectID;
    }

    public Long getEmployesubjectID() {
        return employesubjectID;
    }

    public void setEmployesubjectID(Long employesubjectID) {
        this.employesubjectID = employesubjectID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employesubjectID != null ? employesubjectID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeSubject)) {
            return false;
        }
        EmployeeSubject other = (EmployeeSubject) object;
        if ((this.employesubjectID == null && other.employesubjectID != null) || (this.employesubjectID != null && !this.employesubjectID.equals(other.employesubjectID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.domain.core.EmployeeSubject[ employesubjectID=" + employesubjectID + " ]";
    }
    
}
