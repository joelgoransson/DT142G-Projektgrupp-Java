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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kahre
 */
@Entity
@Table(name = "BILL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b"),
    @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id"),
    @NamedQuery(name = "Bill.findByDate", query = "SELECT b FROM Bill b WHERE b.date = :date"),
    @NamedQuery(name = "Bill.findByStatus", query = "SELECT b FROM Bill b WHERE b.status = :status"),
    @NamedQuery(name = "Bill.findByTime", query = "SELECT b FROM Bill b WHERE b.time = :time")})
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 40)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "TIME")
    @Temporal(TemporalType.TIME)
    private Date time;
    @JoinColumn(name = "EMPLOYEEID", referencedColumnName = "ID")
    @ManyToOne
    private Employee employeeid;
    @JoinColumn(name = "TABLENR", referencedColumnName = "TABLENR")
    @ManyToOne
    private Tables tablenr;

    public Bill() {
    }

    public Bill(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Employee getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Employee employeeid) {
        this.employeeid = employeeid;
    }

    public Tables getTablenr() {
        return tablenr;
    }

    public void setTablenr(Tables tablenr) {
        this.tablenr = tablenr;
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
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Bill[ id=" + id + " ]";
    }
    
}
