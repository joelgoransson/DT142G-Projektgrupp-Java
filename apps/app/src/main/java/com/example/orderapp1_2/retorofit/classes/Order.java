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
    private int billnr;

    public Order(@Element(name = "comment") String comment,@Element(name = "menuitemname") String menuitemname, @Element(name = "billnr") int billnr) {

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

    public int getBillnr() {
        return billnr;
    }

    public void setBillnr(int billnr) {
        this.billnr = billnr;
    }
}
