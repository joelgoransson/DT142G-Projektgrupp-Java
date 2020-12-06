package com.example.orderapp1_2.retorofit.classes;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="orders", strict = false)
public class Orders {
    /*
    @Element(name = "orderid")
    private int orderid;
    */

    @Element(name = "dish",required = false)
    private String dish;

    public Orders(){

    }

    public Orders(//@Element(name = "orderid") int orderid,
                  @Element(name = "dish")  String dish) {
        /*this.orderid = orderid;*/
        this.dish = dish;

    }

    /*
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
    */
    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }


}
