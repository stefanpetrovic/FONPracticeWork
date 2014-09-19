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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Djole
 */
@Entity
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByEmployeeID", query = "SELECT e FROM Employee e WHERE e.employeeID = :employeeID")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "employeeID")
    private Long employeeID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mentor")
    private List<Work> workList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private List<CommisionMember> commisionMemberList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<EmployeeSubject> employeeSubjectList;
    @OneToMany(mappedBy = "chief")
    private List<Department> departmentList;
    @JoinColumn(name = "title", referencedColumnName = "titleID")
    @ManyToOne(optional = false)
    private Title title;
    @JoinColumn(name = "department", referencedColumnName = "departmentID")
    @ManyToOne(optional = false)
    private Department department;
    @JoinColumn(name = "employeeID", referencedColumnName = "personID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public Employee() {
    }

    public Employee(Long employeeID) {
        this.employeeID = employeeID;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
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

    @XmlTransient
    public List<EmployeeSubject> getEmployeeSubjectList() {
        return employeeSubjectList;
    }

    public void setEmployeeSubjectList(List<EmployeeSubject> employeeSubjectList) {
        this.employeeSubjectList = employeeSubjectList;
    }

    @XmlTransient
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
        hash += (employeeID != null ? employeeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employeeID == null && other.employeeID != null) || (this.employeeID != null && !this.employeeID.equals(other.employeeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "dao.domain.core.Employee[ employeeID=" + employeeID + " ]";
        return person.getSurname() + " " + person.getName() + ", " + person.getEmployee().getTitle().getName();
    }
    
}
