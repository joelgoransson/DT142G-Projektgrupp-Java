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
@Table(name = "MENUITEMHASINGREDIENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menuitemhasingredient.findAll", query = "SELECT m FROM Menuitemhasingredient m"),
    @NamedQuery(name = "Menuitemhasingredient.findById", query = "SELECT m FROM Menuitemhasingredient m WHERE m.id = :id"),
    @NamedQuery(name = "Menuitemhasingredient.findByIngredientname", query = "SELECT m FROM Menuitemhasingredient m WHERE m.ingredientname = :ingredientname"),
    @NamedQuery(name = "Menuitemhasingredient.findByMenuitemname", query = "SELECT m FROM Menuitemhasingredient m WHERE m.menuitemname = :menuitemname"),
    @NamedQuery(name = "Menuitemhasingredient.findByQuantity", query = "SELECT m FROM Menuitemhasingredient m WHERE m.quantity = :quantity")})
public class Menuitemhasingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 55)
    @Column(name = "INGREDIENTNAME")
    private String ingredientname;
    @Size(max = 128)
    @Column(name = "MENUITEMNAME")
    private String menuitemname;
    @Column(name = "QUANTITY")
    private Integer quantity;

    public Menuitemhasingredient() {
    }

    public Menuitemhasingredient(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIngredientname() {
        return ingredientname;
    }

    public void setIngredientname(String ingredientname) {
        this.ingredientname = ingredientname;
    }

    public String getMenuitemname() {
        return menuitemname;
    }

    public void setMenuitemname(String menuitemname) {
        this.menuitemname = menuitemname;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        if (!(object instanceof Menuitemhasingredient)) {
            return false;
        }
        Menuitemhasingredient other = (Menuitemhasingredient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Menuitemhasingredient[ id=" + id + " ]";
    }
    
}
