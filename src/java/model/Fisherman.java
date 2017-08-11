/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.vianna
 */
@Entity
@Table(name = "fisherman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fisherman.findAll", query = "SELECT f FROM Fisherman f"),
    @NamedQuery(name = "Fisherman.findByIdFisherman", query = "SELECT f FROM Fisherman f WHERE f.idFisherman = :idFisherman"),
    @NamedQuery(name = "Fisherman.findByRequirementNumber", query = "SELECT f FROM Fisherman f WHERE f.requirementNumber = :requirementNumber"),
    @NamedQuery(name = "Fisherman.findByRequirementDate", query = "SELECT f FROM Fisherman f WHERE f.requirementDate = :requirementDate"),
    @NamedQuery(name = "Fisherman.findByRgp", query = "SELECT f FROM Fisherman f WHERE f.rgp = :rgp"),
    @NamedQuery(name = "Fisherman.findByOrdinanceNumber", query = "SELECT f FROM Fisherman f WHERE f.ordinanceNumber = :ordinanceNumber"),
    @NamedQuery(name = "Fisherman.findByStartedDate", query = "SELECT f FROM Fisherman f WHERE f.startedDate = :startedDate"),
    @NamedQuery(name = "Fisherman.findByEndedDate", query = "SELECT f FROM Fisherman f WHERE f.endedDate = :endedDate")})
public class Fisherman implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFisherman")
    private Integer idFisherman;
    @Basic(optional = false)
    @Column(name = "requirementNumber")
    private String requirementNumber;
    @Basic(optional = false)
    @Column(name = "requirementDate")
    @Temporal(TemporalType.DATE)
    private Date requirementDate;
    @Basic(optional = false)
    @Column(name = "RGP")
    private String rgp;
    @Basic(optional = false)
    @Column(name = "ordinanceNumber")
    private String ordinanceNumber;
    @Basic(optional = false)
    @Column(name = "startedDate")
    private String startedDate;
    @Basic(optional = false)
    @Column(name = "endedDate")
    private String endedDate;
    @JoinColumn(name = "Favored_idFavored", referencedColumnName = "idFavored")
    @ManyToOne(optional = false)
    private Favored favoredidFavored;

    public Fisherman() {
    }

    public Fisherman(Integer idFisherman) {
        this.idFisherman = idFisherman;
    }

    public Fisherman(Integer idFisherman, String requirementNumber, Date requirementDate, String rgp, String ordinanceNumber, String startedDate, String endedDate) {
        this.idFisherman = idFisherman;
        this.requirementNumber = requirementNumber;
        this.requirementDate = requirementDate;
        this.rgp = rgp;
        this.ordinanceNumber = ordinanceNumber;
        this.startedDate = startedDate;
        this.endedDate = endedDate;
    }

    public Integer getIdFisherman() {
        return idFisherman;
    }

    public void setIdFisherman(Integer idFisherman) {
        this.idFisherman = idFisherman;
    }

    public String getRequirementNumber() {
        return requirementNumber;
    }

    public void setRequirementNumber(String requirementNumber) {
        this.requirementNumber = requirementNumber;
    }

    public Date getRequirementDate() {
        return requirementDate;
    }

    public void setRequirementDate(Date requirementDate) {
        this.requirementDate = requirementDate;
    }

    public String getRgp() {
        return rgp;
    }

    public void setRgp(String rgp) {
        this.rgp = rgp;
    }

    public String getOrdinanceNumber() {
        return ordinanceNumber;
    }

    public void setOrdinanceNumber(String ordinanceNumber) {
        this.ordinanceNumber = ordinanceNumber;
    }

    public String getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(String startedDate) {
        this.startedDate = startedDate;
    }

    public String getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(String endedDate) {
        this.endedDate = endedDate;
    }

    public Favored getFavoredidFavored() {
        return favoredidFavored;
    }

    public void setFavoredidFavored(Favored favoredidFavored) {
        this.favoredidFavored = favoredidFavored;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFisherman != null ? idFisherman.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fisherman)) {
            return false;
        }
        Fisherman other = (Fisherman) object;
        if ((this.idFisherman == null && other.idFisherman != null) || (this.idFisherman != null && !this.idFisherman.equals(other.idFisherman))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Fisherman[ idFisherman=" + idFisherman + " ]";
    }
    
}
