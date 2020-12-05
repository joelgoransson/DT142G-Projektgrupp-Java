package com.example.orderapp1_2.retorofit.classes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;




@Root(name="ordermenuitem", strict = false)
public class Order {
    @Element(name = "quantity")
    private int quantity;
    @Element(name = "comment")
    private String comment;
    @Element(name = "orderitemnr")
    private int orderitemnr;
    @Element(name = "menuitemname")
    private String menuitemname;
    @Element(name = "billnr")
    private int billnr;

    public Order( @Element(name = "quantity") int quantity,  @Element(name = "comment") String comment,
                  @Element(name = "orderitemnr") int orderItemId,@Element(name = "menuitemname") String menuItemName,
                  @Element(name = "billnr") int billNr) {
        this.quantity = quantity;
        this.comment = comment;
        this.orderitemnr = orderItemId;
        this.menuitemname = menuItemName;
        this.billnr = billNr;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getComment() {
        return comment;
    }

    public int getOrderItemId() {
        return orderitemnr;
    }

    public String getMenuItemName() {
        return menuitemname;
    }

    public int getBillNr() {
        return billnr;
    }
}
