package com.example.schema.Retrofit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="employeepass", strict = false)
public class EmPassObject {
    @Element(name="id")
    private int id;
    @Element(name="employeename")
    private String employeename;
    @Element(name="passid")
    private int passid;
    @Element(name="employeenr")
    private int employeenr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public int getPassid() {
        return passid;
    }

    public void setPassid(int passid) {
        this.passid = passid;
    }

    public int getEmployeenr() {
        return employeenr;
    }

    public void setEmployeenr(int employeenr) {
        this.employeenr = employeenr;
    }
}
