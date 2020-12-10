/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joaki
 */
@Entity
@Table(name = "PASS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pass.findAll", query = "SELECT p FROM Pass p"),
    @NamedQuery(name = "Pass.findById", query = "SELECT p FROM Pass p WHERE p.id = :id"),
    @NamedQuery(name = "Pass.findByPassnr", query = "SELECT p FROM Pass p WHERE p.passnr = :passnr"),
    @NamedQuery(name = "Pass.findByDate", query = "SELECT p FROM Pass p WHERE p.date = :date"),
    @NamedQuery(name = "Pass.findByWeekday", query = "SELECT p FROM Pass p WHERE p.weekday = :weekday"),
    @NamedQuery(name = "Pass.findByEmployeeid", query = "SELECT p FROM Pass p WHERE p.employeeid = :employeeid")})
public class Pass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PASSNR")
    private Short passnr;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "WEEKDAY")
    private Integer weekday;
    @Column(name = "EMPLOYEEID")
    private Integer employeeid;

    public Pass() {
    }

    public Pass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getPassnr() {
        return passnr;
    }

    public void setPassnr(Short passnr) {
        this.passnr = passnr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
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
        if (!(object instanceof Pass)) {
            return false;
        }
        Pass other = (Pass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pass[ id=" + id + " ]";
    }
    
}
