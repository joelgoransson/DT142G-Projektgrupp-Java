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
@Table(name = "ORDERMENUITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordermenuitem.findAll", query = "SELECT o FROM Ordermenuitem o"),
    @NamedQuery(name = "Ordermenuitem.findByQuantity", query = "SELECT o FROM Ordermenuitem o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "Ordermenuitem.findByComment", query = "SELECT o FROM Ordermenuitem o WHERE o.comment = :comment"),
    @NamedQuery(name = "Ordermenuitem.findByOrderitemnr", query = "SELECT o FROM Ordermenuitem o WHERE o.orderitemnr = :orderitemnr"),
    @NamedQuery(name = "Ordermenuitem.findByMenuitemname", query = "SELECT o FROM Ordermenuitem o WHERE o.menuitemname = :menuitemname"),
    @NamedQuery(name = "Ordermenuitem.findByBillnr", query = "SELECT o FROM Ordermenuitem o WHERE o.billnr = :billnr")})
public class Ordermenuitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    @Size(max = 55)
    @Column(name = "COMMENT")
    private String comment;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDERITEMNR")
    private Integer orderitemnr;
    @Size(max = 128)
    @Column(name = "MENUITEMNAME")
    private String menuitemname;
    @Column(name = "BILLNR")
    private Integer billnr;

    public Ordermenuitem() {
    }

    public Ordermenuitem(Integer orderitemnr) {
        this.orderitemnr = orderitemnr;
    }

    public Ordermenuitem(Integer orderitemnr, int quantity) {
        this.orderitemnr = orderitemnr;
        this.quantity = quantity;
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

    public Integer getOrderitemnr() {
        return orderitemnr;
    }

    public void setOrderitemnr(Integer orderitemnr) {
        this.orderitemnr = orderitemnr;
    }

    public String getMenuitemname() {
        return menuitemname;
    }

    public void setMenuitemname(String menuitemname) {
        this.menuitemname = menuitemname;
    }

    public Integer getBillnr() {
        return billnr;
    }

    public void setBillnr(Integer billnr) {
        this.billnr = billnr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderitemnr != null ? orderitemnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordermenuitem)) {
            return false;
        }
        Ordermenuitem other = (Ordermenuitem) object;
        if ((this.orderitemnr == null && other.orderitemnr != null) || (this.orderitemnr != null && !this.orderitemnr.equals(other.orderitemnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ordermenuitem[ orderitemnr=" + orderitemnr + " ]";
    }
    
}
