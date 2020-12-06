package com.example.orderapp1_2.retorofit.classes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;




@Root(name="ordermenuitem", strict = false)
public class Order {

    @Element(name = "quantity", required = false)
    private int quantity;

    @Element(name = "comment",required = false)
    private String comment;

    @Element(name = "orderitemnr",required = false)
    private int orderitemnr;

    @Element(name = "menuItemName",required = false)
    private String menuitemname;

   @Element(name = "billnr",required = false)
   private int billnr;

    public Order() {

    }

    public Order( @Element(name = "quantity") int quantity,@Element(name = "comment") String comment,@Element(name = "orderitemnr") int orderitemnr,@Element(name = "menuItemName") String menuitemname,@Element(name = "billnr")  int billnr ) {
        this.quantity = quantity;
        this.comment = comment;
        this.orderitemnr = orderitemnr;
        this.menuitemname = menuitemname;
        this.billnr = billnr;
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

    public int getOrderitemnr() {
        return orderitemnr;
    }

    public void setOrderitemnr(int orderitemnr) {
        this.orderitemnr = orderitemnr;
    }

    public String getMenuitemname() {
        return menuitemname;
    }

    public void setMenuitemname(String menuitemname) {
        this.menuitemname = menuitemname;
    }

    public int getBillnr() {
        return billnr;
    }

    public void setBillnr(int billnr) {
        this.billnr = billnr;
    }
}
