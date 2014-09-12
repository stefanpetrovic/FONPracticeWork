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
@Table(name = "keywords")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Keywords.findAll", query = "SELECT k FROM Keywords k"),
    @NamedQuery(name = "Keywords.findByKeywordsID", query = "SELECT k FROM Keywords k WHERE k.keywordsID = :keywordsID"),
    @NamedQuery(name = "Keywords.findByKeyword", query = "SELECT k FROM Keywords k WHERE k.keyword = :keyword")})
public class Keywords implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "keywordsID")
    private Long keywordsID;
    @Basic(optional = false)
    @Column(name = "keyword")
    private String keyword;
    @JoinColumn(name = "work", referencedColumnName = "workID")
    @ManyToOne(optional = false)
    private Work work;

    public Keywords() {
    }

    public Keywords(Long keywordsID) {
        this.keywordsID = keywordsID;
    }

    public Keywords(Long keywordsID, String keyword) {
        this.keywordsID = keywordsID;
        this.keyword = keyword;
    }

    public Long getKeywordsID() {
        return keywordsID;
    }

    public void setKeywordsID(Long keywordsID) {
        this.keywordsID = keywordsID;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keywordsID != null ? keywordsID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Keywords)) {
            return false;
        }
        Keywords other = (Keywords) object;
        if ((this.keywordsID == null && other.keywordsID != null) || (this.keywordsID != null && !this.keywordsID.equals(other.keywordsID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.domain.core.Keywords[ keywordsID=" + keywordsID + " ]";
    }
    
}
