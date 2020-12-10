/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joaki
 */
@Entity
@Table(name = "EMPLOYEEPASSES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employeepasses.findAll", query = "SELECT e FROM Employeepasses e"),
    @NamedQuery(name = "Employeepasses.findByBsid", query = "SELECT e FROM Employeepasses e WHERE e.bsid = :bsid"),
    @NamedQuery(name = "Employeepasses.findByEmployeeid", query = "SELECT e FROM Employeepasses e WHERE e.employeeid = :employeeid"),
    @NamedQuery(name = "Employeepasses.findByPassid", query = "SELECT e FROM Employeepasses e WHERE e.passid = :passid")})
public class Employeepasses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BSID")
    private Integer bsid;
    @Column(name = "EMPLOYEEID")
    private Integer employeeid;
    @Column(name = "PASSID")
    private Integer passid;

    public Employeepasses() {
    }

    public Employeepasses(Integer bsid) {
        this.bsid = bsid;
    }

    public Integer getBsid() {
        return bsid;
    }

    public void setBsid(Integer bsid) {
        this.bsid = bsid;
    }

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public Integer getPassid() {
        return passid;
    }

    public void setPassid(Integer passid) {
        this.passid = passid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bsid != null ? bsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employeepasses)) {
            return false;
        }
        Employeepasses other = (Employeepasses) object;
        if ((this.bsid == null && other.bsid != null) || (this.bsid != null && !this.bsid.equals(other.bsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Employeepasses[ bsid=" + bsid + " ]";
    }
    
}
