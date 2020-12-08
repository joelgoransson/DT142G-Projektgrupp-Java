package com.example.orderapp1_2.retorofit.classes;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="orders", strict = false)
public class Orders {

    @Element(name = "tableid",required = false)
    private int tableid;


    @Element(name = "dish",required = false)
    private String dish;

    public Orders(){

    }

    public Orders(@Element(name = "tableid") int tableid,
                  @Element(name = "dish")  String dish) {
        this.tableid = tableid;
        this.dish = dish;

    }


    public int getOrderid() {
        return tableid;
    }

    public void setOrderid(int orderid) {
        this.tableid = tableid;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }


}
