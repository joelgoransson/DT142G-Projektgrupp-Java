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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joaki
 */
@Entity
@Table(name = "EMPLOYEEPASS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employeepass.findAll", query = "SELECT e FROM Employeepass e"),
    @NamedQuery(name = "Employeepass.findById", query = "SELECT e FROM Employeepass e WHERE e.id = :id"),
    @NamedQuery(name = "Employeepass.findByEmployeename", query = "SELECT e FROM Employeepass e WHERE e.employeename = :employeename"),
    @NamedQuery(name = "Employeepass.findByPassid", query = "SELECT e FROM Employeepass e WHERE e.passid = :passid")})
public class Employeepass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 55)
    @Column(name = "EMPLOYEENAME")
    private String employeename;
    @Column(name = "PASSID")
    private Integer passid;

    public Employeepass() {
    }

    public Employeepass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employeepass)) {
            return false;
        }
        Employeepass other = (Employeepass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Employeepass[ id=" + id + " ]";
    }
    
}
