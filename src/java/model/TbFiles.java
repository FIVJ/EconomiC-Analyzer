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
@Table(name = "tb_files")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbFiles.findAll", query = "SELECT t FROM TbFiles t")
    , @NamedQuery(name = "TbFiles.findByIdFile", query = "SELECT t FROM TbFiles t WHERE t.idFile = :idFile")
    , @NamedQuery(name = "TbFiles.findByStrNameFile", query = "SELECT t FROM TbFiles t WHERE t.strNameFile = :strNameFile")
    , @NamedQuery(name = "TbFiles.findByStrMonth", query = "SELECT t FROM TbFiles t WHERE t.strMonth = :strMonth")
    , @NamedQuery(name = "TbFiles.findByStrYear", query = "SELECT t FROM TbFiles t WHERE t.strYear = :strYear")})
public class TbFiles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_file")
    private Integer idFile;
    @Basic(optional = false)
    @Column(name = "str_name_file")
    private String strNameFile;
    @Column(name = "str_month")
    private String strMonth;
    @Column(name = "str_year")
    private String strYear;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbFilesIdFile")
    private Collection<TbPayments> tbPaymentsCollection;

    public TbFiles() {
    }

    public TbFiles(Integer idFile) {
        this.idFile = idFile;
    }

    public TbFiles(Integer idFile, String strNameFile) {
        this.idFile = idFile;
        this.strNameFile = strNameFile;
    }

    public Integer getIdFile() {
        return idFile;
    }

    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
    }

    public String getStrNameFile() {
        return strNameFile;
    }

    public void setStrNameFile(String strNameFile) {
        this.strNameFile = strNameFile;
    }

    public String getStrMonth() {
        return strMonth;
    }

    public void setStrMonth(String strMonth) {
        this.strMonth = strMonth;
    }

    public String getStrYear() {
        return strYear;
    }

    public void setStrYear(String strYear) {
        this.strYear = strYear;
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
        hash += (idFile != null ? idFile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbFiles)) {
            return false;
        }
        TbFiles other = (TbFiles) object;
        if ((this.idFile == null && other.idFile != null) || (this.idFile != null && !this.idFile.equals(other.idFile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbFiles[ idFile=" + idFile + " ]";
    }
    
}
