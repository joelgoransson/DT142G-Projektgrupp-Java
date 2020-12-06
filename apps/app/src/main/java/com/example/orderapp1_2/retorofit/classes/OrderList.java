package com.example.orderapp1_2.retorofit.classes;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="ordermenuitems", strict=false)
public class OrderList {

    @ElementList(name = "ordermenuitem", inline=true, required = false)
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}

