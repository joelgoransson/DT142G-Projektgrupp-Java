package com.example.schema.Retrofit;

import com.example.schema.PassCard;

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
    @Element(name="week")

    public EmPassObject(){}

    public EmPassObject(PassCard passCard){
        id = passCard.getId();
        employeename = passCard.getEmpName();
        passid = passCard.getPassId();
    }

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
}
