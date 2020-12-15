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
@Table(name = "WIPPASS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wippass.findAll", query = "SELECT w FROM Wippass w"),
    @NamedQuery(name = "Wippass.findById", query = "SELECT w FROM Wippass w WHERE w.id = :id"),
    @NamedQuery(name = "Wippass.findByPassnr", query = "SELECT w FROM Wippass w WHERE w.passnr = :passnr"),
    @NamedQuery(name = "Wippass.findByWeek", query = "SELECT w FROM Wippass w WHERE w.week = :week"),
    @NamedQuery(name = "Wippass.findByDay", query = "SELECT w FROM Wippass w WHERE w.day = :day")})
public class Wippass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PASSNR")
    private Integer passnr;
    @Column(name = "WEEK")
    private Integer week;
    @Column(name = "DAY")
    private Integer day;

    public Wippass() {
    }

    public Wippass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPassnr() {
        return passnr;
    }

    public void setPassnr(Integer passnr) {
        this.passnr = passnr;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
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
        if (!(object instanceof Wippass)) {
            return false;
        }
        Wippass other = (Wippass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Wippass[ id=" + id + " ]";
    }
    
}
