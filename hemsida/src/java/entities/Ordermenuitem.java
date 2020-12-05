/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author Joel
 */
@Entity
@Table(name = "ORDERMENUITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordermenuitem.findAll", query = "SELECT o FROM Ordermenuitem o"),
    @NamedQuery(name = "Ordermenuitem.findByQuantity", query = "SELECT o FROM Ordermenuitem o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "Ordermenuitem.findByComment", query = "SELECT o FROM Ordermenuitem o WHERE o.comment = :comment"),
    @NamedQuery(name = "Ordermenuitem.findByOrderitemnr", query = "SELECT o FROM Ordermenuitem o WHERE o.ordermenuitemPK.orderitemnr = :orderitemnr"),
    @NamedQuery(name = "Ordermenuitem.findByBillnr", query = "SELECT o FROM Ordermenuitem o WHERE o.ordermenuitemPK.billnr = :billnr")})
public class Ordermenuitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrdermenuitemPK ordermenuitemPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    @Size(max = 55)
    @Column(name = "COMMENT")
    private String comment;
    @JoinColumn(name = "BILLNR", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Bill bill;
    @JoinColumn(name = "MENUITEMNAME", referencedColumnName = "NAME")
    @ManyToOne
    private Menuitem menuitemname;

    public Ordermenuitem() {
    }

    public Ordermenuitem(OrdermenuitemPK ordermenuitemPK) {
        this.ordermenuitemPK = ordermenuitemPK;
    }

    public Ordermenuitem(OrdermenuitemPK ordermenuitemPK, int quantity) {
        this.ordermenuitemPK = ordermenuitemPK;
        this.quantity = quantity;
    }

    public Ordermenuitem(int orderitemnr, int billnr) {
        this.ordermenuitemPK = new OrdermenuitemPK(orderitemnr, billnr);
    }

    public OrdermenuitemPK getOrdermenuitemPK() {
        return ordermenuitemPK;
    }

    public void setOrdermenuitemPK(OrdermenuitemPK ordermenuitemPK) {
        this.ordermenuitemPK = ordermenuitemPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Menuitem getMenuitemname() {
        return menuitemname;
    }

    public void setMenuitemname(Menuitem menuitemname) {
        this.menuitemname = menuitemname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordermenuitemPK != null ? ordermenuitemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordermenuitem)) {
            return false;
        }
        Ordermenuitem other = (Ordermenuitem) object;
        if ((this.ordermenuitemPK == null && other.ordermenuitemPK != null) || (this.ordermenuitemPK != null && !this.ordermenuitemPK.equals(other.ordermenuitemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ordermenuitem[ ordermenuitemPK=" + ordermenuitemPK + " ]";
    }
    
}
