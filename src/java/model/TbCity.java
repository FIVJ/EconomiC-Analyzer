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
 * @author aluno
 */
@Entity
@Table(name = "tb_city")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbCity.findAll", query = "SELECT t FROM TbCity t")
    , @NamedQuery(name = "TbCity.findByIdCity", query = "SELECT t FROM TbCity t WHERE t.idCity = :idCity")
    , @NamedQuery(name = "TbCity.findByStrNameCity", query = "SELECT t FROM TbCity t WHERE t.strNameCity = :strNameCity")
    , @NamedQuery(name = "TbCity.findByStrCodSiafiCity", query = "SELECT t FROM TbCity t WHERE t.strCodSiafiCity = :strCodSiafiCity")})
public class TbCity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_city")
    private Integer idCity;
    @Column(name = "str_name_city")
    private String strNameCity;
    @Basic(optional = false)
    @Column(name = "str_cod_siafi_city")
    private String strCodSiafiCity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbCityIdCity")
    private Collection<TbPayments> tbPaymentsCollection;
    @JoinColumn(name = "tb_state_id_state", referencedColumnName = "id_state")
    @ManyToOne(optional = false)
    private TbState tbStateIdState;

    public TbCity() {
    }

    public TbCity(Integer idCity) {
        this.idCity = idCity;
    }

    public TbCity(Integer idCity, String strCodSiafiCity) {
        this.idCity = idCity;
        this.strCodSiafiCity = strCodSiafiCity;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getStrNameCity() {
        return strNameCity;
    }

    public void setStrNameCity(String strNameCity) {
        this.strNameCity = strNameCity;
    }

    public String getStrCodSiafiCity() {
        return strCodSiafiCity;
    }

    public void setStrCodSiafiCity(String strCodSiafiCity) {
        this.strCodSiafiCity = strCodSiafiCity;
    }

    @XmlTransient
    public Collection<TbPayments> getTbPaymentsCollection() {
        return tbPaymentsCollection;
    }

    public void setTbPaymentsCollection(Collection<TbPayments> tbPaymentsCollection) {
        this.tbPaymentsCollection = tbPaymentsCollection;
    }

    public TbState getTbStateIdState() {
        return tbStateIdState;
    }

    public void setTbStateIdState(TbState tbStateIdState) {
        this.tbStateIdState = tbStateIdState;
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
        if (!(object instanceof TbCity)) {
            return false;
        }
        TbCity other = (TbCity) object;
        if ((this.idCity == null && other.idCity != null) || (this.idCity != null && !this.idCity.equals(other.idCity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbCity[ idCity=" + idCity + " ]";
    }
    
}
