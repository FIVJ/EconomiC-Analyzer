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
@Table(name = "tb_action")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbAction.findAll", query = "SELECT t FROM TbAction t")
    , @NamedQuery(name = "TbAction.findByIdAction", query = "SELECT t FROM TbAction t WHERE t.idAction = :idAction")
    , @NamedQuery(name = "TbAction.findByStrCodAction", query = "SELECT t FROM TbAction t WHERE t.strCodAction = :strCodAction")
    , @NamedQuery(name = "TbAction.findByStrNameAction", query = "SELECT t FROM TbAction t WHERE t.strNameAction = :strNameAction")})
public class TbAction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_action")
    private Integer idAction;
    @Basic(optional = false)
    @Column(name = "str_cod_action")
    private String strCodAction;
    @Column(name = "str_name_action")
    private String strNameAction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbActionIdAction")
    private Collection<TbPayments> tbPaymentsCollection;

    public TbAction() {
    }

    public TbAction(Integer idAction) {
        this.idAction = idAction;
    }

    public TbAction(Integer idAction, String strCodAction) {
        this.idAction = idAction;
        this.strCodAction = strCodAction;
    }

    public Integer getIdAction() {
        return idAction;
    }

    public void setIdAction(Integer idAction) {
        this.idAction = idAction;
    }

    public String getStrCodAction() {
        return strCodAction;
    }

    public void setStrCodAction(String strCodAction) {
        this.strCodAction = strCodAction;
    }

    public String getStrNameAction() {
        return strNameAction;
    }

    public void setStrNameAction(String strNameAction) {
        this.strNameAction = strNameAction;
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
        hash += (idAction != null ? idAction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbAction)) {
            return false;
        }
        TbAction other = (TbAction) object;
        if ((this.idAction == null && other.idAction != null) || (this.idAction != null && !this.idAction.equals(other.idAction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbAction[ idAction=" + idAction + " ]";
    }
    
}
