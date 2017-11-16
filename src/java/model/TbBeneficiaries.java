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
@Table(name = "tb_beneficiaries")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbBeneficiaries.findAll", query = "SELECT t FROM TbBeneficiaries t")
    , @NamedQuery(name = "TbBeneficiaries.findByIdBeneficiaries", query = "SELECT t FROM TbBeneficiaries t WHERE t.idBeneficiaries = :idBeneficiaries")
    , @NamedQuery(name = "TbBeneficiaries.findByStrNis", query = "SELECT t FROM TbBeneficiaries t WHERE t.strNis = :strNis")
    , @NamedQuery(name = "TbBeneficiaries.findByStrNamePerson", query = "SELECT t FROM TbBeneficiaries t WHERE t.strNamePerson = :strNamePerson")})
public class TbBeneficiaries implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_beneficiaries")
    private Long idBeneficiaries;
    @Basic(optional = false)
    @Column(name = "str_nis")
    private String strNis;
    @Basic(optional = false)
    @Column(name = "str_name_person")
    private String strNamePerson;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbBeneficiariesIdBeneficiaries")
    private Collection<TbPayments> tbPaymentsCollection;

    public TbBeneficiaries() {
    }

    public TbBeneficiaries(Long idBeneficiaries) {
        this.idBeneficiaries = idBeneficiaries;
    }

    public TbBeneficiaries(Long idBeneficiaries, String strNis, String strNamePerson) {
        this.idBeneficiaries = idBeneficiaries;
        this.strNis = strNis;
        this.strNamePerson = strNamePerson;
    }

    public Long getIdBeneficiaries() {
        return idBeneficiaries;
    }

    public void setIdBeneficiaries(Long idBeneficiaries) {
        this.idBeneficiaries = idBeneficiaries;
    }

    public String getStrNis() {
        return strNis;
    }

    public void setStrNis(String strNis) {
        this.strNis = strNis;
    }

    public String getStrNamePerson() {
        return strNamePerson;
    }

    public void setStrNamePerson(String strNamePerson) {
        this.strNamePerson = strNamePerson;
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
        hash += (idBeneficiaries != null ? idBeneficiaries.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbBeneficiaries)) {
            return false;
        }
        TbBeneficiaries other = (TbBeneficiaries) object;
        if ((this.idBeneficiaries == null && other.idBeneficiaries != null) || (this.idBeneficiaries != null && !this.idBeneficiaries.equals(other.idBeneficiaries))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbBeneficiaries[ idBeneficiaries=" + idBeneficiaries + " ]";
    }
    
}
