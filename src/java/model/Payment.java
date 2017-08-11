/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucas.vianna
 */
@Entity
@Table(name = "payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p"),
    @NamedQuery(name = "Payment.findByIdPayment", query = "SELECT p FROM Payment p WHERE p.idPayment = :idPayment"),
    @NamedQuery(name = "Payment.findByValue", query = "SELECT p FROM Payment p WHERE p.value = :value"),
    @NamedQuery(name = "Payment.findByReferenceMonth", query = "SELECT p FROM Payment p WHERE p.referenceMonth = :referenceMonth"),
    @NamedQuery(name = "Payment.findByMonthCompetence", query = "SELECT p FROM Payment p WHERE p.monthCompetence = :monthCompetence"),
    @NamedQuery(name = "Payment.findByWithdrawalDate", query = "SELECT p FROM Payment p WHERE p.withdrawalDate = :withdrawalDate"),
    @NamedQuery(name = "Payment.findByPlotDate", query = "SELECT p FROM Payment p WHERE p.plotDate = :plotDate"),
    @NamedQuery(name = "Payment.findByPlotNumber", query = "SELECT p FROM Payment p WHERE p.plotNumber = :plotNumber"),
    @NamedQuery(name = "Payment.findByRepaymentDate", query = "SELECT p FROM Payment p WHERE p.repaymentDate = :repaymentDate"),
    @NamedQuery(name = "Payment.findByRepaymentValeu", query = "SELECT p FROM Payment p WHERE p.repaymentValeu = :repaymentValeu")})
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPayment")
    private Integer idPayment;
    @Basic(optional = false)
    @Column(name = "value")
    private float value;
    @Column(name = "referenceMonth")
    private Integer referenceMonth;
    @Column(name = "monthCompetence")
    private Integer monthCompetence;
    @Column(name = "withdrawalDate")
    @Temporal(TemporalType.DATE)
    private Date withdrawalDate;
    @Column(name = "plotDate")
    @Temporal(TemporalType.DATE)
    private Date plotDate;
    @Column(name = "plotNumber")
    private Integer plotNumber;
    @Column(name = "repaymentDate")
    @Temporal(TemporalType.DATE)
    private Date repaymentDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "repaymentValeu")
    private Float repaymentValeu;
    @ManyToMany(mappedBy = "paymentCollection")
    private Collection<Favored> favoredCollection;

    public Payment() {
    }

    public Payment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public Payment(Integer idPayment, float value) {
        this.idPayment = idPayment;
        this.value = value;
    }

    public Integer getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Integer getReferenceMonth() {
        return referenceMonth;
    }

    public void setReferenceMonth(Integer referenceMonth) {
        this.referenceMonth = referenceMonth;
    }

    public Integer getMonthCompetence() {
        return monthCompetence;
    }

    public void setMonthCompetence(Integer monthCompetence) {
        this.monthCompetence = monthCompetence;
    }

    public Date getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    public Date getPlotDate() {
        return plotDate;
    }

    public void setPlotDate(Date plotDate) {
        this.plotDate = plotDate;
    }

    public Integer getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(Integer plotNumber) {
        this.plotNumber = plotNumber;
    }

    public Date getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public Float getRepaymentValeu() {
        return repaymentValeu;
    }

    public void setRepaymentValeu(Float repaymentValeu) {
        this.repaymentValeu = repaymentValeu;
    }

    @XmlTransient
    public Collection<Favored> getFavoredCollection() {
        return favoredCollection;
    }

    public void setFavoredCollection(Collection<Favored> favoredCollection) {
        this.favoredCollection = favoredCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPayment != null ? idPayment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.idPayment == null && other.idPayment != null) || (this.idPayment != null && !this.idPayment.equals(other.idPayment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Payment[ idPayment=" + idPayment + " ]";
    }
    
}
