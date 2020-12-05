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
 * @author Joel
 */
@Entity
@Table(name = "DAY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Day.findAll", query = "SELECT d FROM Day d"),
    @NamedQuery(name = "Day.findByName", query = "SELECT d FROM Day d WHERE d.name = :name"),
    @NamedQuery(name = "Day.findById", query = "SELECT d FROM Day d WHERE d.id = :id")})
public class Day implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 8)
    @Column(name = "NAME")
    private String name;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Short id;

    public Day() {
    }

    public Day(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
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
        if (!(object instanceof Day)) {
            return false;
        }
        Day other = (Day) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Day[ id=" + id + " ]";
    }
    
}
