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
@Table(name = "tb_functions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbFunctions.findAll", query = "SELECT t FROM TbFunctions t")
    , @NamedQuery(name = "TbFunctions.findByIdFunction", query = "SELECT t FROM TbFunctions t WHERE t.idFunction = :idFunction")
    , @NamedQuery(name = "TbFunctions.findByStrCodFunction", query = "SELECT t FROM TbFunctions t WHERE t.strCodFunction = :strCodFunction")
    , @NamedQuery(name = "TbFunctions.findByStrNameFunction", query = "SELECT t FROM TbFunctions t WHERE t.strNameFunction = :strNameFunction")})
public class TbFunctions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_function")
    private Integer idFunction;
    @Basic(optional = false)
    @Column(name = "str_cod_function")
    private String strCodFunction;
    @Column(name = "str_name_function")
    private String strNameFunction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbFunctionsIdFunction")
    private Collection<TbPayments> tbPaymentsCollection;

    public TbFunctions() {
    }

    public TbFunctions(Integer idFunction) {
        this.idFunction = idFunction;
    }

    public TbFunctions(Integer idFunction, String strCodFunction) {
        this.idFunction = idFunction;
        this.strCodFunction = strCodFunction;
    }

    public Integer getIdFunction() {
        return idFunction;
    }

    public void setIdFunction(Integer idFunction) {
        this.idFunction = idFunction;
    }

    public String getStrCodFunction() {
        return strCodFunction;
    }

    public void setStrCodFunction(String strCodFunction) {
        this.strCodFunction = strCodFunction;
    }

    public String getStrNameFunction() {
        return strNameFunction;
    }

    public void setStrNameFunction(String strNameFunction) {
        this.strNameFunction = strNameFunction;
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
        hash += (idFunction != null ? idFunction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbFunctions)) {
            return false;
        }
        TbFunctions other = (TbFunctions) object;
        if ((this.idFunction == null && other.idFunction != null) || (this.idFunction != null && !this.idFunction.equals(other.idFunction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbFunctions[ idFunction=" + idFunction + " ]";
    }
    
}
