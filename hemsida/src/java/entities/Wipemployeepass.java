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
@Table(name = "WIPEMPLOYEEPASS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wipemployeepass.findAll", query = "SELECT w FROM Wipemployeepass w"),
    @NamedQuery(name = "Wipemployeepass.findById", query = "SELECT w FROM Wipemployeepass w WHERE w.id = :id"),
    @NamedQuery(name = "Wipemployeepass.findByPassid", query = "SELECT w FROM Wipemployeepass w WHERE w.passid = :passid"),
    @NamedQuery(name = "Wipemployeepass.findByEmployeename", query = "SELECT w FROM Wipemployeepass w WHERE w.employeename = :employeename")})
public class Wipemployeepass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PASSID")
    private Integer passid;
    @Size(max = 55)
    @Column(name = "EMPLOYEENAME")
    private String employeename;

    public Wipemployeepass() {
    }

    public Wipemployeepass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPassid() {
        return passid;
    }

    public void setPassid(Integer passid) {
        this.passid = passid;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
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
        if (!(object instanceof Wipemployeepass)) {
            return false;
        }
        Wipemployeepass other = (Wipemployeepass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Wipemployeepass[ id=" + id + " ]";
    }
    
}
