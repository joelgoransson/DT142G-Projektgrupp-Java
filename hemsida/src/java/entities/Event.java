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
 * @author kahre
 */
@Entity
@Table(name = "EVENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
    @NamedQuery(name = "Event.updateById", query = "UPDATE Event e SET e.name = :name WHERE e.id = :id"),
    @NamedQuery(name = "Event.findById", query = "SELECT e FROM Event e WHERE e.id = :id"),
    @NamedQuery(name = "Event.deleteById", query = "DELETE FROM Event e WHERE e.id = :id"),
    @NamedQuery(name = "Event.findByName", query = "SELECT e FROM Event e WHERE e.name = :name"),
    @NamedQuery(name = "Event.sortByID", query = "SELECT e FROM Event e ORDER BY e.id desc"),
    @NamedQuery(name = "Event.findByClock", query = "SELECT e FROM Event e WHERE e.clock = :clock"),
    @NamedQuery(name = "Event.findByDay", query = "SELECT e FROM Event e WHERE e.day = :day"),
    @NamedQuery(name = "Event.findByImage", query = "SELECT e FROM Event e WHERE e.image = :image"),
    @NamedQuery(name = "Event.findByOpeningact", query = "SELECT e FROM Event e WHERE e.openingact = :openingact"),
    @NamedQuery(name = "Event.findByDescription", query = "SELECT e FROM Event e WHERE e.description = :description")})
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "NAME")
    private String name;
    @Size(max = 50)
    @Column(name = "CLOCK")
    private String clock;
    @Size(max = 50)
    @Column(name = "DAY")
    private String day;
    @Size(max = 500)
    @Column(name = "IMAGE")
    private String image;
    @Size(max = 20)
    @Column(name = "OPENINGACT")
    private String openingact;
    @Size(max = 50)
    @Column(name = "DESCRIPTION")
    private String description;

    public Event() {
    }

    public Event(Integer id) {
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

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOpeningact() {
        return openingact;
    }

    public void setOpeningact(String openingact) {
        this.openingact = openingact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Event[ id=" + id + " ]";
    }
    
}
