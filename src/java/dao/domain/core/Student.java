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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Djole
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByStudentID", query = "SELECT s FROM Student s WHERE s.studentID = :studentID"),
    @NamedQuery(name = "Student.findByIndexNo", query = "SELECT s FROM Student s WHERE s.indexNo = :indexNo"),
    @NamedQuery(name = "Student.findByJmbg", query = "SELECT s FROM Student s WHERE s.jmbg = :jmbg")})
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "studentID")
    private Long studentID;
    @Basic(optional = false)
    @Column(name = "indexNo")
    private String indexNo;
    @Basic(optional = false)
    @Column(name = "jmbg")
    private String jmbg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Work> workList;
    @JoinColumn(name = "course", referencedColumnName = "courseID")
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "studentID", referencedColumnName = "personID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public Student() {
    }

    public Student(Long studentID) {
        this.studentID = studentID;
    }

    public Student(Long studentID, String indexNo, String jmbg) {
        this.studentID = studentID;
        this.indexNo = indexNo;
        this.jmbg = jmbg;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    @XmlTransient
    public List<Work> getWorkList() {
        return workList;
    }

    public void setWorkList(List<Work> workList) {
        this.workList = workList;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
        hash += (studentID != null ? studentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.studentID == null && other.studentID != null) || (this.studentID != null && !this.studentID.equals(other.studentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.domain.core.Student[ studentID=" + studentID + " ]";
    }
    
}
