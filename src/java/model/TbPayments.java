/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "tb_payments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbPayments.findAll", query = "SELECT t FROM TbPayments t")
    , @NamedQuery(name = "TbPayments.findByIdPayment", query = "SELECT t FROM TbPayments t WHERE t.idPayment = :idPayment")
    , @NamedQuery(name = "TbPayments.findByDbValue", query = "SELECT t FROM TbPayments t WHERE t.dbValue = :dbValue")})
public class TbPayments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_payment")
    private Long idPayment;
    @Basic(optional = false)
    @Column(name = "db_value")
    private double dbValue;
    @JoinColumn(name = "tb_action_id_action", referencedColumnName = "id_action")
    @ManyToOne(optional = false)
    private TbAction tbActionIdAction;
    @JoinColumn(name = "tb_beneficiaries_id_beneficiaries", referencedColumnName = "id_beneficiaries")
    @ManyToOne(optional = false)
    private TbBeneficiaries tbBeneficiariesIdBeneficiaries;
    @JoinColumn(name = "tb_city_id_city", referencedColumnName = "id_city")
    @ManyToOne(optional = false)
    private TbCity tbCityIdCity;
    @JoinColumn(name = "tb_files_id_file", referencedColumnName = "id_file")
    @ManyToOne(optional = false)
    private TbFiles tbFilesIdFile;
    @JoinColumn(name = "tb_functions_id_function", referencedColumnName = "id_function")
    @ManyToOne(optional = false)
    private TbFunctions tbFunctionsIdFunction;
    @JoinColumn(name = "tb_program_id_program", referencedColumnName = "id_program")
    @ManyToOne(optional = false)
    private TbProgram tbProgramIdProgram;
    @JoinColumn(name = "tb_source_id_source", referencedColumnName = "id_source")
    @ManyToOne(optional = false)
    private TbSource tbSourceIdSource;
    @JoinColumn(name = "tb_subfunctions_id_subfunction", referencedColumnName = "id_subfunction")
    @ManyToOne(optional = false)
    private TbSubfunctions tbSubfunctionsIdSubfunction;

    public TbPayments() {
    }

    public TbPayments(Long idPayment) {
        this.idPayment = idPayment;
    }

    public TbPayments(Long idPayment, double dbValue) {
        this.idPayment = idPayment;
        this.dbValue = dbValue;
    }

    public Long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }

    public double getDbValue() {
        return dbValue;
    }

    public void setDbValue(double dbValue) {
        this.dbValue = dbValue;
    }

    public TbAction getTbActionIdAction() {
        return tbActionIdAction;
    }

    public void setTbActionIdAction(TbAction tbActionIdAction) {
        this.tbActionIdAction = tbActionIdAction;
    }

    public TbBeneficiaries getTbBeneficiariesIdBeneficiaries() {
        return tbBeneficiariesIdBeneficiaries;
    }

    public void setTbBeneficiariesIdBeneficiaries(TbBeneficiaries tbBeneficiariesIdBeneficiaries) {
        this.tbBeneficiariesIdBeneficiaries = tbBeneficiariesIdBeneficiaries;
    }

    public TbCity getTbCityIdCity() {
        return tbCityIdCity;
    }

    public void setTbCityIdCity(TbCity tbCityIdCity) {
        this.tbCityIdCity = tbCityIdCity;
    }

    public TbFiles getTbFilesIdFile() {
        return tbFilesIdFile;
    }

    public void setTbFilesIdFile(TbFiles tbFilesIdFile) {
        this.tbFilesIdFile = tbFilesIdFile;
    }

    public TbFunctions getTbFunctionsIdFunction() {
        return tbFunctionsIdFunction;
    }

    public void setTbFunctionsIdFunction(TbFunctions tbFunctionsIdFunction) {
        this.tbFunctionsIdFunction = tbFunctionsIdFunction;
    }

    public TbProgram getTbProgramIdProgram() {
        return tbProgramIdProgram;
    }

    public void setTbProgramIdProgram(TbProgram tbProgramIdProgram) {
        this.tbProgramIdProgram = tbProgramIdProgram;
    }

    public TbSource getTbSourceIdSource() {
        return tbSourceIdSource;
    }

    public void setTbSourceIdSource(TbSource tbSourceIdSource) {
        this.tbSourceIdSource = tbSourceIdSource;
    }

    public TbSubfunctions getTbSubfunctionsIdSubfunction() {
        return tbSubfunctionsIdSubfunction;
    }

    public void setTbSubfunctionsIdSubfunction(TbSubfunctions tbSubfunctionsIdSubfunction) {
        this.tbSubfunctionsIdSubfunction = tbSubfunctionsIdSubfunction;
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
        if (!(object instanceof TbPayments)) {
            return false;
        }
        TbPayments other = (TbPayments) object;
        if ((this.idPayment == null && other.idPayment != null) || (this.idPayment != null && !this.idPayment.equals(other.idPayment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbPayments[ idPayment=" + idPayment + " ]";
    }
    
}
