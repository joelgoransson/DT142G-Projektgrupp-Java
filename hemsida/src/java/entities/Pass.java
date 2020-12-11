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
@Table(name = "PASS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pass.findAll", query = "SELECT p FROM Pass p"),
    @NamedQuery(name = "Pass.findById", query = "SELECT p FROM Pass p WHERE p.id = :id"),
    @NamedQuery(name = "Pass.findByPass", query = "SELECT p FROM Pass p WHERE p.pass = :pass"),
    @NamedQuery(name = "Pass.findByWeekday", query = "SELECT p FROM Pass p WHERE p.weekday = :weekday"),
    @NamedQuery(name = "Pass.findByWeeknr", query = "SELECT p FROM Pass p WHERE p.weeknr = :weeknr")})
public class Pass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PASS")
    private Integer pass;
    @Column(name = "WEEKDAY")
    private Integer weekday;
    @Column(name = "WEEKNR")
    private Integer weeknr;

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

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    public Integer getWeeknr() {
        return weeknr;
    }

    public void setWeeknr(Integer weeknr) {
        this.weeknr = weeknr;
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
