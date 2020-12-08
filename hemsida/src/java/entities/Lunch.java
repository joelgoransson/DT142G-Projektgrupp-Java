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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kahre
 */
@Entity
@Table(name = "LUNCH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lunch.findAll", query = "SELECT l FROM Lunch l"),
    @NamedQuery(name = "Lunch.findById", query = "SELECT l FROM Lunch l WHERE l.id = :id"),
    @NamedQuery(name = "Lunch.findByDay", query = "SELECT l FROM Lunch l WHERE l.weekday.id = :day"),
    @NamedQuery(name = "Lunch.updateById", query = "UPDATE Lunch l SET l.name = :name WHERE l.id = :id"),
    @NamedQuery(name = "Lunch.findByName", query = "SELECT l FROM Lunch l WHERE l.name = :name")})
public class Lunch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 128)
    @Column(name = "NAME")
    private String name;
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "WEEKDAY", referencedColumnName = "ID")
    @ManyToOne
    private Weekday weekday;

    public Lunch() {
    }

    public Lunch(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
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
        if (!(object instanceof Lunch)) {
            return false;
        }
        Lunch other = (Lunch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Lunch[ id=" + id + " ]";
    }
    
}
