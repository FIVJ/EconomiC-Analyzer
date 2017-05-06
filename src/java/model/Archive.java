/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Humberto
 */
@Entity
@Table(name = "archive")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Archive.findAll", query = "SELECT a FROM Archive a"),
    @NamedQuery(name = "Archive.findByIdArchive", query = "SELECT a FROM Archive a WHERE a.idArchive = :idArchive"),
    @NamedQuery(name = "Archive.findByName", query = "SELECT a FROM Archive a WHERE a.name = :name"),
    @NamedQuery(name = "Archive.findByTimeImport", query = "SELECT a FROM Archive a WHERE a.timeImport = :timeImport")})
public class Archive implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArchive")
    private Integer idArchive;
    @Column(name = "Name")
    private String name;
    @Column(name = "Time_Import")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeImport;

    public Archive() {
    }

    public Archive(Integer idArchive) {
        this.idArchive = idArchive;
    }

    public Integer getIdArchive() {
        return idArchive;
    }

    public void setIdArchive(Integer idArchive) {
        this.idArchive = idArchive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimeImport() {
        return timeImport;
    }

    public void setTimeImport(Date timeImport) {
        this.timeImport = timeImport;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArchive != null ? idArchive.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Archive)) {
            return false;
        }
        Archive other = (Archive) object;
        if ((this.idArchive == null && other.idArchive != null) || (this.idArchive != null && !this.idArchive.equals(other.idArchive))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Archive[ idArchive=" + idArchive + " ]";
    }
    
}
