/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.domain.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Djole
 */
@Entity
@Table(name = "work")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Work.findAll", query = "SELECT w FROM Work w"),
    @NamedQuery(name = "Work.findByWorkID", query = "SELECT w FROM Work w WHERE w.workID = :workID"),
    @NamedQuery(name = "Work.findByTitle", query = "SELECT w FROM Work w WHERE w.title = :title"),
    @NamedQuery(name = "Work.findByFinalFileURI", query = "SELECT w FROM Work w WHERE w.finalFileURI = :finalFileURI"),
    @NamedQuery(name = "Work.findByAcceptanceDate", query = "SELECT w FROM Work w WHERE w.acceptanceDate = :acceptanceDate"),
    @NamedQuery(name = "Work.findByExamDate", query = "SELECT w FROM Work w WHERE w.examDate = :examDate"),
    @NamedQuery(name = "Work.findByGrade", query = "SELECT w FROM Work w WHERE w.grade = :grade"),
    @NamedQuery(name = "Work.findByStatus", query = "SELECT w FROM Work w WHERE w.status = :status")})
public class Work implements Serializable {
    @Column(name = "grade")
    private Integer grade;
    @Column(name = "status")
    private Boolean status;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "workID")
    private Long workID;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "finalFileURI")
    private String finalFileURI;
    @Basic(optional = false)
    @Column(name = "acceptanceDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acceptanceDate;
    @Basic(optional = false)
    @Column(name = "examDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date examDate;
    @JoinColumn(name = "mentor", referencedColumnName = "employeeID")
    @ManyToOne(optional = false)
    private Employee mentor;
    @JoinColumn(name = "student", referencedColumnName = "studentID")
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "commision", referencedColumnName = "commisionID")
    @ManyToOne(optional = false)
    private Commision commision;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "work")
    private Collection<Keywords> keywordsCollection;

    public Work() {
    }

    public Work(Long workID) {
        this.workID = workID;
    }

    public Work(Long workID, String title, String finalFileURI, Date acceptanceDate, Date examDate, int grade, boolean status) {
        this.workID = workID;
        this.title = title;
        this.finalFileURI = finalFileURI;
        this.acceptanceDate = acceptanceDate;
        this.examDate = examDate;
        this.grade = grade;
        this.status = status;
    }

    public Long getWorkID() {
        return workID;
    }

    public void setWorkID(Long workID) {
        this.workID = workID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFinalFileURI() {
        return finalFileURI;
    }

    public void setFinalFileURI(String finalFileURI) {
        this.finalFileURI = finalFileURI;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }


    public Employee getMentor() {
        return mentor;
    }

    public void setMentor(Employee mentor) {
        this.mentor = mentor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Commision getCommision() {
        return commision;
    }

    public void setCommision(Commision commision) {
        this.commision = commision;
    }

    @XmlTransient
    public Collection<Keywords> getKeywordsCollection() {
        return keywordsCollection;
    }

    public void setKeywordsCollection(Collection<Keywords> keywordsCollection) {
        this.keywordsCollection = keywordsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workID != null ? workID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Work)) {
            return false;
        }
        Work other = (Work) object;
        if ((this.workID == null && other.workID != null) || (this.workID != null && !this.workID.equals(other.workID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.domain.core.Work[ workID=" + workID + " ]";
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
