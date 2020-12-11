package com.example.orderapp1_2;

import com.example.orderapp1_2.retorofit.classes.Order;

import java.util.ArrayList;

public class CardItem {
    private int billid;
    private String bordTV;
    private String status;
    private String time;
    ArrayList<Order> orders = new ArrayList<>();

    public CardItem(int billid, String bordTV,String status,String time, ArrayList<Order> orders )
    {
        this.billid = billid;
        this.bordTV = bordTV;
        this.orders = orders;
        this.status = status;
        this.time = time;
    }
    public CardItem(int billid, String bordTV, String status,String time)
    {
        this.billid = billid;
        this.bordTV = bordTV;
        this.status = status;
        this.time = time;

    }
    public int getBillid() {
        return billid;
    }

    public void setBillid(int billid) {
        this.billid = billid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
