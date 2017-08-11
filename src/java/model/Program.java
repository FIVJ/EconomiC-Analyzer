/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucas.vianna
 */
@Entity
@Table(name = "program")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Program.findAll", query = "SELECT p FROM Program p"),
    @NamedQuery(name = "Program.findByIdProgram", query = "SELECT p FROM Program p WHERE p.idProgram = :idProgram"),
    @NamedQuery(name = "Program.findByCodProgram", query = "SELECT p FROM Program p WHERE p.codProgram = :codProgram"),
    @NamedQuery(name = "Program.findByName", query = "SELECT p FROM Program p WHERE p.name = :name")})
public class Program implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProgram")
    private Integer idProgram;
    @Basic(optional = false)
    @Column(name = "codProgram")
    private int codProgram;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "programCollection")
    private Collection<Favored> favoredCollection;

    public Program() {
    }

    public Program(Integer idProgram) {
        this.idProgram = idProgram;
    }

    public Program(Integer idProgram, int codProgram) {
        this.idProgram = idProgram;
        this.codProgram = codProgram;
    }

    public Integer getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(Integer idProgram) {
        this.idProgram = idProgram;
    }

    public int getCodProgram() {
        return codProgram;
    }

    public void setCodProgram(int codProgram) {
        this.codProgram = codProgram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (idProgram != null ? idProgram.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Program)) {
            return false;
        }
        Program other = (Program) object;
        if ((this.idProgram == null && other.idProgram != null) || (this.idProgram != null && !this.idProgram.equals(other.idProgram))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Program[ idProgram=" + idProgram + " ]";
    }
    
}
