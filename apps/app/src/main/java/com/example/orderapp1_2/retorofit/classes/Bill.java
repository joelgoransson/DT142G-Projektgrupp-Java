package com.example.orderapp1_2.retorofit.classes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.sql.Timestamp;

@Root(name="bill", strict = false)
public class Bill {
    @Element(name = "status", required = false) //Namnet på det elementet i API
    private String status;

    @Element(name = "tablenr", required = false) //Namnet på det elementet i API
    private int tablenr;

    @Element(name = "employeeid", required = false) //Namnet på det elementet i API
    private int employeeid;

    @Element(name = "time", required = false) //Namnet på det elementet i API
    private Timestamp time;

    public Bill( @Element(name = "status") String status,
                 @Element(name = "tablenr") int tablenr,
                 @Element(name = "employeeid") int employeeid,
                 @Element(name = "time") Timestamp time
                 ) {

        this.status = status;
        this.tablenr = tablenr;
        this.employeeid = employeeid;
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTablenr() {
        return tablenr;
    }

    public void setTablenr(int tablenr) {
        this.tablenr = tablenr;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }


}
