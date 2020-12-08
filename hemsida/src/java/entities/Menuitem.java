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
@Table(name = "MENUITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menuitem.findAll", query = "SELECT m FROM Menuitem m"),
    @NamedQuery(name = "Menuitem.findByName", query = "SELECT m FROM Menuitem m WHERE m.name = :name"),
    @NamedQuery(name = "Menuitem.findByPrice", query = "SELECT m FROM Menuitem m WHERE m.price = :price"),
    @NamedQuery(name = "Menuitem.findByType", query = "SELECT m FROM Menuitem m WHERE m.type = :type"),
    @NamedQuery(name = "Menuitem.deleteByName", query = "DELETE FROM Menuitem m WHERE m.name = :name"),
    @NamedQuery(name = "Menuitem.findByPreptime", query = "SELECT m FROM Menuitem m WHERE m.preptime = :preptime")})
public class Menuitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private Integer price;
    @Size(max = 55)
    @Column(name = "TYPE")
    private String type;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PREPTIME")
    private Double preptime;

    public Menuitem() {
    }

    public Menuitem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPreptime() {
        return preptime;
    }

    public void setPreptime(Double preptime) {
        this.preptime = preptime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menuitem)) {
            return false;
        }
        Menuitem other = (Menuitem) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Menuitem[ name=" + name + " ]";
    }
    
}
