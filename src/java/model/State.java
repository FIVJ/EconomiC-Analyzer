/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucas.vianna
 */
@Entity
@Table(name = "state")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "State.findAll", query = "SELECT s FROM State s"),
    @NamedQuery(name = "State.findByIdState", query = "SELECT s FROM State s WHERE s.idState = :idState"),
    @NamedQuery(name = "State.findByName", query = "SELECT s FROM State s WHERE s.name = :name"),
    @NamedQuery(name = "State.findByAbbreviation", query = "SELECT s FROM State s WHERE s.abbreviation = :abbreviation")})
public class State implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idState")
    private Integer idState;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "abbreviation")
    private String abbreviation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stateidState")
    private Collection<City> cityCollection;
    @JoinColumn(name = "Region_idRegion", referencedColumnName = "idRegion")
    @ManyToOne(optional = false)
    private Region regionidRegion;

    public State() {
    }

    public State(Integer idState) {
        this.idState = idState;
    }

    public State(Integer idState, String name, String abbreviation) {
        this.idState = idState;
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @XmlTransient
    public Collection<City> getCityCollection() {
        return cityCollection;
    }

    public void setCityCollection(Collection<City> cityCollection) {
        this.cityCollection = cityCollection;
    }

    public Region getRegionidRegion() {
        return regionidRegion;
    }

    public void setRegionidRegion(Region regionidRegion) {
        this.regionidRegion = regionidRegion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idState != null ? idState.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof State)) {
            return false;
        }
        State other = (State) object;
        if ((this.idState == null && other.idState != null) || (this.idState != null && !this.idState.equals(other.idState))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.State[ idState=" + idState + " ]";
    }
    
}
