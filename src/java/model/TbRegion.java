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
@Table(name = "tb_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbRegion.findAll", query = "SELECT t FROM TbRegion t")
    , @NamedQuery(name = "TbRegion.findByIdRegion", query = "SELECT t FROM TbRegion t WHERE t.idRegion = :idRegion")
    , @NamedQuery(name = "TbRegion.findByStrNameRegion", query = "SELECT t FROM TbRegion t WHERE t.strNameRegion = :strNameRegion")})
public class TbRegion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_region")
    private Integer idRegion;
    @Column(name = "str_name_region")
    private String strNameRegion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbRegionIdRegion")
    private Collection<TbState> tbStateCollection;

    public TbRegion() {
    }

    public TbRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public String getStrNameRegion() {
        return strNameRegion;
    }

    public void setStrNameRegion(String strNameRegion) {
        this.strNameRegion = strNameRegion;
    }

    @XmlTransient
    public Collection<TbState> getTbStateCollection() {
        return tbStateCollection;
    }

    public void setTbStateCollection(Collection<TbState> tbStateCollection) {
        this.tbStateCollection = tbStateCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegion != null ? idRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbRegion)) {
            return false;
        }
        TbRegion other = (TbRegion) object;
        if ((this.idRegion == null && other.idRegion != null) || (this.idRegion != null && !this.idRegion.equals(other.idRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbRegion[ idRegion=" + idRegion + " ]";
    }
    
}
