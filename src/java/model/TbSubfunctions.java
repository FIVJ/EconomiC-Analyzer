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
@Table(name = "tb_subfunctions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSubfunctions.findAll", query = "SELECT t FROM TbSubfunctions t")
    , @NamedQuery(name = "TbSubfunctions.findByIdSubfunction", query = "SELECT t FROM TbSubfunctions t WHERE t.idSubfunction = :idSubfunction")
    , @NamedQuery(name = "TbSubfunctions.findByStrCodSubfunction", query = "SELECT t FROM TbSubfunctions t WHERE t.strCodSubfunction = :strCodSubfunction")
    , @NamedQuery(name = "TbSubfunctions.findByStrNameSubfunction", query = "SELECT t FROM TbSubfunctions t WHERE t.strNameSubfunction = :strNameSubfunction")})
public class TbSubfunctions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_subfunction")
    private Integer idSubfunction;
    @Basic(optional = false)
    @Column(name = "str_cod_subfunction")
    private String strCodSubfunction;
    @Column(name = "str_name_subfunction")
    private String strNameSubfunction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbSubfunctionsIdSubfunction")
    private Collection<TbPayments> tbPaymentsCollection;

    public TbSubfunctions() {
    }

    public TbSubfunctions(Integer idSubfunction) {
        this.idSubfunction = idSubfunction;
    }

    public TbSubfunctions(Integer idSubfunction, String strCodSubfunction) {
        this.idSubfunction = idSubfunction;
        this.strCodSubfunction = strCodSubfunction;
    }

    public Integer getIdSubfunction() {
        return idSubfunction;
    }

    public void setIdSubfunction(Integer idSubfunction) {
        this.idSubfunction = idSubfunction;
    }

    public String getStrCodSubfunction() {
        return strCodSubfunction;
    }

    public void setStrCodSubfunction(String strCodSubfunction) {
        this.strCodSubfunction = strCodSubfunction;
    }

    public String getStrNameSubfunction() {
        return strNameSubfunction;
    }

    public void setStrNameSubfunction(String strNameSubfunction) {
        this.strNameSubfunction = strNameSubfunction;
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
        hash += (idSubfunction != null ? idSubfunction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbSubfunctions)) {
            return false;
        }
        TbSubfunctions other = (TbSubfunctions) object;
        if ((this.idSubfunction == null && other.idSubfunction != null) || (this.idSubfunction != null && !this.idSubfunction.equals(other.idSubfunction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbSubfunctions[ idSubfunction=" + idSubfunction + " ]";
    }
    
}
