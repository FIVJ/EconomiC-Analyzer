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
@Table(name = "tb_source")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSource.findAll", query = "SELECT t FROM TbSource t")
    , @NamedQuery(name = "TbSource.findByIdSource", query = "SELECT t FROM TbSource t WHERE t.idSource = :idSource")
    , @NamedQuery(name = "TbSource.findByStrGoal", query = "SELECT t FROM TbSource t WHERE t.strGoal = :strGoal")
    , @NamedQuery(name = "TbSource.findByStrOrigin", query = "SELECT t FROM TbSource t WHERE t.strOrigin = :strOrigin")
    , @NamedQuery(name = "TbSource.findByStrPeriodicity", query = "SELECT t FROM TbSource t WHERE t.strPeriodicity = :strPeriodicity")})
public class TbSource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_source")
    private Integer idSource;
    @Basic(optional = false)
    @Column(name = "str_goal")
    private String strGoal;
    @Column(name = "str_origin")
    private String strOrigin;
    @Column(name = "str_periodicity")
    private String strPeriodicity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbSourceIdSource")
    private Collection<TbPayments> tbPaymentsCollection;

    public TbSource() {
    }

    public TbSource(Integer idSource) {
        this.idSource = idSource;
    }

    public TbSource(Integer idSource, String strGoal) {
        this.idSource = idSource;
        this.strGoal = strGoal;
    }

    public Integer getIdSource() {
        return idSource;
    }

    public void setIdSource(Integer idSource) {
        this.idSource = idSource;
    }

    public String getStrGoal() {
        return strGoal;
    }

    public void setStrGoal(String strGoal) {
        this.strGoal = strGoal;
    }

    public String getStrOrigin() {
        return strOrigin;
    }

    public void setStrOrigin(String strOrigin) {
        this.strOrigin = strOrigin;
    }

    public String getStrPeriodicity() {
        return strPeriodicity;
    }

    public void setStrPeriodicity(String strPeriodicity) {
        this.strPeriodicity = strPeriodicity;
    }

    @XmlTransient
    public Collection<TbPayments> getTbPaymentsCollection() {
        return tbPaymentsCollection;
    }

    public void setTbPaymentsCollection(Collection<TbPayments> tbPaymentsCollection) {
        this.tbPaymentsCollection = tbPaymentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSource != null ? idSource.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbSource)) {
            return false;
        }
        TbSource other = (TbSource) object;
        if ((this.idSource == null && other.idSource != null) || (this.idSource != null && !this.idSource.equals(other.idSource))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbSource[ idSource=" + idSource + " ]";
    }
    
}
