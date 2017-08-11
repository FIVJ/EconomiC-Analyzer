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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "city")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "City.findAll", query = "SELECT c FROM City c"),
    @NamedQuery(name = "City.findByIdCity", query = "SELECT c FROM City c WHERE c.idCity = :idCity"),
    @NamedQuery(name = "City.findByCodIBGE", query = "SELECT c FROM City c WHERE c.codIBGE = :codIBGE"),
    @NamedQuery(name = "City.findByCodSIAFI", query = "SELECT c FROM City c WHERE c.codSIAFI = :codSIAFI"),
    @NamedQuery(name = "City.findByName", query = "SELECT c FROM City c WHERE c.name = :name")})
public class City implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCity")
    private Integer idCity;
    @Column(name = "codIBGE")
    private String codIBGE;
    @Column(name = "codSIAFI")
    private String codSIAFI;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "State_idState", referencedColumnName = "idState")
    @ManyToOne(optional = false)
    private State stateidState;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityidCity")
    private Collection<Favored> favoredCollection;

    public City() {
    }

    public City(Integer idCity) {
        this.idCity = idCity;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getCodIBGE() {
        return codIBGE;
    }

    public void setCodIBGE(String codIBGE) {
        this.codIBGE = codIBGE;
    }

    public String getCodSIAFI() {
        return codSIAFI;
    }

    public void setCodSIAFI(String codSIAFI) {
        this.codSIAFI = codSIAFI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getStateidState() {
        return stateidState;
    }

    public void setStateidState(State stateidState) {
        this.stateidState = stateidState;
    }

    @XmlTransient
    public Collection<Favored> getFavoredCollection() {
        return favoredCollection;
    }

    public void setFavoredCollection(Collection<Favored> favoredCollection) {
        this.favoredCollection = favoredCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCity != null ? idCity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.idCity == null && other.idCity != null) || (this.idCity != null && !this.idCity.equals(other.idCity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.City[ idCity=" + idCity + " ]";
    }
    
}
