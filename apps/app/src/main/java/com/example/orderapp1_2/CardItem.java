package com.example.orderapp1_2;

import com.example.orderapp1_2.retorofit.classes.Order;

import java.util.ArrayList;

public class CardItem {
    private int billid;
    private String bordTV;
    ArrayList<Order> orders = new ArrayList<>();

    public CardItem(int billid, String bordTV, ArrayList<Order> orders)
    {
        this.billid = billid;
        this.bordTV = bordTV;
        this.orders = orders;
    }
    public CardItem(int billid, String bordTV)
    {
        this.billid = billid;
        this.bordTV = bordTV;

    }

    public int getBillid() {
        return billid;
    }

    public void setBillid(int billid) {
        this.billid = billid;
    }

    public String getBordTV() {
        return bordTV;
    }

    public void setBordTV(String bordTV) {
        this.bordTV = bordTV;
    }

    public ArrayList<Order> getOrdersTV() {
        return orders;
    }

    public void setOrdersTV(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
