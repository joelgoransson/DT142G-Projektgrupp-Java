/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    @NamedQuery(name = "Menuitemhasingredient.findByIngredientname", query = "SELECT m FROM Menuitemhasingredient m WHERE m.menuitemhasingredientPK.ingredientname = :ingredientname"),
    @NamedQuery(name = "Menuitemhasingredient.findByMenuitemname", query = "SELECT m FROM Menuitemhasingredient m WHERE m.menuitemhasingredientPK.menuitemname = :menuitemname"),
    @NamedQuery(name = "Menuitemhasingredient.findByQuantity", query = "SELECT m FROM Menuitemhasingredient m WHERE m.quantity = :quantity")})
public class Menuitemhasingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MenuitemhasingredientPK menuitemhasingredientPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QUANTITY")
    private Double quantity;
    @JoinColumn(name = "INGREDIENTNAME", referencedColumnName = "NAME", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ingredient ingredient;
    @JoinColumn(name = "MENUITEMNAME", referencedColumnName = "NAME", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Menuitem menuitem;

    public Menuitemhasingredient() {
    }

    public Menuitemhasingredient(MenuitemhasingredientPK menuitemhasingredientPK) {
        this.menuitemhasingredientPK = menuitemhasingredientPK;
    }

    public Menuitemhasingredient(String ingredientname, String menuitemname) {
        this.menuitemhasingredientPK = new MenuitemhasingredientPK(ingredientname, menuitemname);
    }

    public MenuitemhasingredientPK getMenuitemhasingredientPK() {
        return menuitemhasingredientPK;
    }

    public void setMenuitemhasingredientPK(MenuitemhasingredientPK menuitemhasingredientPK) {
        this.menuitemhasingredientPK = menuitemhasingredientPK;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Menuitem getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(Menuitem menuitem) {
        this.menuitem = menuitem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuitemhasingredientPK != null ? menuitemhasingredientPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menuitemhasingredient)) {
            return false;
        }
        Menuitemhasingredient other = (Menuitemhasingredient) object;
        if ((this.menuitemhasingredientPK == null && other.menuitemhasingredientPK != null) || (this.menuitemhasingredientPK != null && !this.menuitemhasingredientPK.equals(other.menuitemhasingredientPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Menuitemhasingredient[ menuitemhasingredientPK=" + menuitemhasingredientPK + " ]";
    }
    
}
