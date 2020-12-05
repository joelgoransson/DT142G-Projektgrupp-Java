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

/**
 *
 * @author Joel
 */
@Embeddable
public class OrdermenuitemPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDERITEMNR")
    private int orderitemnr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BILLNR")
    private int billnr;

    public OrdermenuitemPK() {
    }

    public OrdermenuitemPK(int orderitemnr, int billnr) {
        this.orderitemnr = orderitemnr;
        this.billnr = billnr;
    }

    public int getOrderitemnr() {
        return orderitemnr;
    }

    public void setOrderitemnr(int orderitemnr) {
        this.orderitemnr = orderitemnr;
    }

    public int getBillnr() {
        return billnr;
    }

    public void setBillnr(int billnr) {
        this.billnr = billnr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orderitemnr;
        hash += (int) billnr;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdermenuitemPK)) {
            return false;
        }
        OrdermenuitemPK other = (OrdermenuitemPK) object;
        if (this.orderitemnr != other.orderitemnr) {
            return false;
        }
        if (this.billnr != other.billnr) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrdermenuitemPK[ orderitemnr=" + orderitemnr + ", billnr=" + billnr + " ]";
    }
    
}
