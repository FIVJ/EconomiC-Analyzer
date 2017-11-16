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
@Table(name = "tb_program")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbProgram.findAll", query = "SELECT t FROM TbProgram t")
    , @NamedQuery(name = "TbProgram.findByIdProgram", query = "SELECT t FROM TbProgram t WHERE t.idProgram = :idProgram")
    , @NamedQuery(name = "TbProgram.findByStrCodProgram", query = "SELECT t FROM TbProgram t WHERE t.strCodProgram = :strCodProgram")
    , @NamedQuery(name = "TbProgram.findByStrNameProgram", query = "SELECT t FROM TbProgram t WHERE t.strNameProgram = :strNameProgram")})
public class TbProgram implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_program")
    private Integer idProgram;
    @Basic(optional = false)
    @Column(name = "str_cod_program")
    private String strCodProgram;
    @Column(name = "str_name_program")
    private String strNameProgram;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbProgramIdProgram")
    private Collection<TbPayments> tbPaymentsCollection;

    public TbProgram() {
    }

    public TbProgram(Integer idProgram) {
        this.idProgram = idProgram;
    }

    public TbProgram(Integer idProgram, String strCodProgram) {
        this.idProgram = idProgram;
        this.strCodProgram = strCodProgram;
    }

    public Integer getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(Integer idProgram) {
        this.idProgram = idProgram;
    }

    public String getStrCodProgram() {
        return strCodProgram;
    }

    public void setStrCodProgram(String strCodProgram) {
        this.strCodProgram = strCodProgram;
    }

    public String getStrNameProgram() {
        return strNameProgram;
    }

    public void setStrNameProgram(String strNameProgram) {
        this.strNameProgram = strNameProgram;
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
        hash += (idProgram != null ? idProgram.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbProgram)) {
            return false;
        }
        TbProgram other = (TbProgram) object;
        if ((this.idProgram == null && other.idProgram != null) || (this.idProgram != null && !this.idProgram.equals(other.idProgram))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbProgram[ idProgram=" + idProgram + " ]";
    }
    
}
