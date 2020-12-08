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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kahre
 */
@Entity
@Table(name = "PASS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pass.findAll", query = "SELECT p FROM Pass p"),
    @NamedQuery(name = "Pass.findById", query = "SELECT p FROM Pass p WHERE p.id = :id"),
    @NamedQuery(name = "Pass.findByStarttime", query = "SELECT p FROM Pass p WHERE p.starttime = :starttime"),
    @NamedQuery(name = "Pass.findByStoptime", query = "SELECT p FROM Pass p WHERE p.stoptime = :stoptime"),
    @NamedQuery(name = "Pass.findByDate", query = "SELECT p FROM Pass p WHERE p.date = :date"),
    @NamedQuery(name = "Pass.findByEmployeeid", query = "SELECT p FROM Pass p WHERE p.employeeid = :employeeid")})
public class Pass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 40)
    @Column(name = "STARTTIME")
    private String starttime;
    @Size(max = 40)
    @Column(name = "STOPTIME")
    private String stoptime;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getStoptime() {
        return stoptime;
    }

    public void setStoptime(String stoptime) {
        this.stoptime = stoptime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
