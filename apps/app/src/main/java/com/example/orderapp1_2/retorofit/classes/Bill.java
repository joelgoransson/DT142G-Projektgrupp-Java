package com.example.orderapp1_2.retorofit.classes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.sql.Timestamp;

@Root(name="bill", strict = false)
public class Bill {
    @Element(name = "id", required = false) //Namnet på det elementet i API
    private int id;

    @Element(name = "status", required = false) //Namnet på det elementet i API
    private String status;

    @Element(name = "tablenr", required = false) //Namnet på det elementet i API
    private int tablenr;

    @Element(name = "employeeid", required = false) //Namnet på det elementet i API
    private int employeeid;

    @Element(name = "time", required = false) //Namnet på det elementet i API
    private String time;

    public Bill( @Element(name = "status") String status,
                 @Element(name = "tablenr") int tablenr,
                 @Element(name = "employeeid") int employeeid,
                 @Element(name = "time") String time
                 ) {

        this.status = status;
        this.tablenr = tablenr;
        this.employeeid = employeeid;
        this.time = time;
    }

    public Bill( @Element(name = "id") int id,
                 @Element(name = "status") String status,
                 @Element(name = "tablenr") int tablenr,
                 @Element(name = "employeeid") int employeeid,
                 @Element(name = "time") String time
    ) {
        this.id = id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
