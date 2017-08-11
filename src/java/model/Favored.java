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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "favored")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Favored.findAll", query = "SELECT f FROM Favored f"),
    @NamedQuery(name = "Favored.findByIdFavored", query = "SELECT f FROM Favored f WHERE f.idFavored = :idFavored"),
    @NamedQuery(name = "Favored.findByName", query = "SELECT f FROM Favored f WHERE f.name = :name"),
    @NamedQuery(name = "Favored.findByNis", query = "SELECT f FROM Favored f WHERE f.nis = :nis"),
    @NamedQuery(name = "Favored.findByCpf", query = "SELECT f FROM Favored f WHERE f.cpf = :cpf")})
public class Favored implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFavored")
    private Integer idFavored;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "nis")
    private String nis;
    @Column(name = "CPF")
    private String cpf;
    @JoinTable(name = "payment_has_favored", joinColumns = {
        @JoinColumn(name = "Favored_idFavored", referencedColumnName = "idFavored")}, inverseJoinColumns = {
        @JoinColumn(name = "Payment_idPayment", referencedColumnName = "idPayment")})
    @ManyToMany
    private Collection<Payment> paymentCollection;
    @JoinTable(name = "favored_has_program", joinColumns = {
        @JoinColumn(name = "Favored_idFavored", referencedColumnName = "idFavored")}, inverseJoinColumns = {
        @JoinColumn(name = "Program_idProgram", referencedColumnName = "idProgram")})
    @ManyToMany
    private Collection<Program> programCollection;
    @JoinColumn(name = "City_idCity", referencedColumnName = "idCity")
    @ManyToOne(optional = false)
    private City cityidCity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "favoredidFavored")
    private Collection<Fisherman> fishermanCollection;

    public Favored() {
    }

    public Favored(Integer idFavored) {
        this.idFavored = idFavored;
    }

    public Favored(Integer idFavored, String name, String nis) {
        this.idFavored = idFavored;
        this.name = name;
        this.nis = nis;
    }

    public Integer getIdFavored() {
        return idFavored;
    }

    public void setIdFavored(Integer idFavored) {
        this.idFavored = idFavored;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @XmlTransient
    public Collection<Payment> getPaymentCollection() {
        return paymentCollection;
    }

    public void setPaymentCollection(Collection<Payment> paymentCollection) {
        this.paymentCollection = paymentCollection;
    }

    @XmlTransient
    public Collection<Program> getProgramCollection() {
        return programCollection;
    }

    public void setProgramCollection(Collection<Program> programCollection) {
        this.programCollection = programCollection;
    }

    public City getCityidCity() {
        return cityidCity;
    }

    public void setCityidCity(City cityidCity) {
        this.cityidCity = cityidCity;
    }

    @XmlTransient
    public Collection<Fisherman> getFishermanCollection() {
        return fishermanCollection;
    }

    public void setFishermanCollection(Collection<Fisherman> fishermanCollection) {
        this.fishermanCollection = fishermanCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFavored != null ? idFavored.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favored)) {
            return false;
        }
        Favored other = (Favored) object;
        if ((this.idFavored == null && other.idFavored != null) || (this.idFavored != null && !this.idFavored.equals(other.idFavored))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Favored[ idFavored=" + idFavored + " ]";
    }
    
}
