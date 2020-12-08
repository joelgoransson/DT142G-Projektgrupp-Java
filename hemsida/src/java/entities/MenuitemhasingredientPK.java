/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kahre
 */
@Embeddable
public class MenuitemhasingredientPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "INGREDIENTNAME")
    private String ingredientname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "MENUITEMNAME")
    private String menuitemname;

    public MenuitemhasingredientPK() {
    }

    public MenuitemhasingredientPK(String ingredientname, String menuitemname) {
        this.ingredientname = ingredientname;
        this.menuitemname = menuitemname;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingredientname != null ? ingredientname.hashCode() : 0);
        hash += (menuitemname != null ? menuitemname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuitemhasingredientPK)) {
            return false;
        }
        MenuitemhasingredientPK other = (MenuitemhasingredientPK) object;
        if ((this.ingredientname == null && other.ingredientname != null) || (this.ingredientname != null && !this.ingredientname.equals(other.ingredientname))) {
            return false;
        }
        if ((this.menuitemname == null && other.menuitemname != null) || (this.menuitemname != null && !this.menuitemname.equals(other.menuitemname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MenuitemhasingredientPK[ ingredientname=" + ingredientname + ", menuitemname=" + menuitemname + " ]";
    }
    
}
