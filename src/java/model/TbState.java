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
@Table(name = "tb_state")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbState.findAll", query = "SELECT t FROM TbState t")
    , @NamedQuery(name = "TbState.findByIdState", query = "SELECT t FROM TbState t WHERE t.idState = :idState")
    , @NamedQuery(name = "TbState.findByStrUf", query = "SELECT t FROM TbState t WHERE t.strUf = :strUf")
    , @NamedQuery(name = "TbState.findByStrName", query = "SELECT t FROM TbState t WHERE t.strName = :strName")})
public class TbState implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_state")
    private Integer idState;
    @Basic(optional = false)
    @Column(name = "str_uf")
    private String strUf;
    @Column(name = "str_name")
    private String strName;
    @JoinColumn(name = "tb_region_id_region", referencedColumnName = "id_region")
    @ManyToOne(optional = false)
    private TbRegion tbRegionIdRegion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbStateIdState")
    private Collection<TbCity> tbCityCollection;

    public TbState() {
    }

    public TbState(Integer idState) {
        this.idState = idState;
    }

    public TbState(Integer idState, String strUf) {
        this.idState = idState;
        this.strUf = strUf;
    }

    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }

    public String getStrUf() {
        return strUf;
    }

    public void setStrUf(String strUf) {
        this.strUf = strUf;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public TbRegion getTbRegionIdRegion() {
        return tbRegionIdRegion;
    }

    public void setTbRegionIdRegion(TbRegion tbRegionIdRegion) {
        this.tbRegionIdRegion = tbRegionIdRegion;
    }

    @XmlTransient
    public Collection<TbCity> getTbCityCollection() {
        return tbCityCollection;
    }

    public void setTbCityCollection(Collection<TbCity> tbCityCollection) {
        this.tbCityCollection = tbCityCollection;
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
        if (!(object instanceof TbState)) {
            return false;
        }
        TbState other = (TbState) object;
        if ((this.idState == null && other.idState != null) || (this.idState != null && !this.idState.equals(other.idState))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbState[ idState=" + idState + " ]";
    }
    
}
