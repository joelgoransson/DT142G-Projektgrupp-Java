package com.example.schema.Retrofit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="pass", strict=false)
public class PassObject {

    @Element(name="id")
    private int id;

    @Element(name="pass")
    private int passnr;

    @Element(name="weekday")
    private int weekday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassnr() {
        return passnr;
    }

    public void setPassnr(int passnr) {
        this.passnr = passnr;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }
}
