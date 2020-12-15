package com.example.schema.Retrofit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="pass", strict=false)
public class PassObject {
    @Element(name="id")
    private int id;

    @Element(name="pass")
    private int pass;

    @Element(name="weekday")
    private int weekday;

    @Element(name="weeknr")
    private int weeknr;

    @Override
    public String toString() {
        return "PassObject{" +
                "id=" + id +
                ", pass=" + pass +
                ", weekday=" + weekday +
                ", weeknr=" + weeknr +
                '}';
    }

    public PassObject() {
    }

    public PassObject(int pass, int weekday, int weeknr) {
        this.pass = pass;
        this.weekday = weekday;
        this.weeknr = weeknr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public int getWeeknr() {
        return weeknr;
    }

    public void setWeeknr(int weeknr) {
        this.weeknr = weeknr;
    }
}
