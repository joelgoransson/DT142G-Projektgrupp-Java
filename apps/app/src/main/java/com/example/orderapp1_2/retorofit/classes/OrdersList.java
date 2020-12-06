package com.example.orderapp1_2.retorofit.classes;


import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="orderss", strict=false)
public class OrdersList {
    @ElementList(name = "orders", inline=true)
    private List<Orders> ordersList;

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }
}
