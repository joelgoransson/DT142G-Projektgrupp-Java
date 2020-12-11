package com.example.orderapp1_2.retorofit.classes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;




@Root(name="ordermenuitem", strict = false)
public class Order {



    @Element(name = "comment",required = false)
    private String comment;

    @Element(name = "menuitemname",required = false)
    private String menuitemname;

    @Element(name = "billnr",required = false)
    private Integer billnr;

    @Element(name = "preptime",required = false)
    private Double preptime;

    public Order(@Element(name = "comment") String comment,
                 @Element(name = "menuitemname") String menuitemname,
                 @Element(name = "billnr") Integer billnr,
                 @Element(name = "preptime") Double preptime) {

        this.comment = comment;
        this.menuitemname = menuitemname;
        this.billnr = billnr;
        this.preptime = preptime;
    }
    public Order(@Element(name = "comment") String comment,
                 @Element(name = "menuitemname") String menuitemname,
                 @Element(name = "billnr") Integer billnr
                 ) {

        this.comment = comment;
        this.menuitemname = menuitemname;
        this.billnr = billnr;

    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Double getPreptime() {
        return preptime;
    }

    public void setPreptime(Double preptime) {
        this.preptime = preptime;
    }
}
