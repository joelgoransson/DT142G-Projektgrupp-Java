package com.example.schema;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PassCard{
    private int id;
    private int empNr;
    private int passId;
    private int passNr;
    private String weekday;
    private String empName;
    private int weekNr;

    @Override
    public String toString() {
        return "PassCard{" +
                "id=" + id +
                ", empNr=" + empNr +
                ", passId=" + passId +
                ", passNr=" + passNr +
                ", weekday='" + weekday + '\'' +
                ", empName='" + empName + '\'' +
                '}';
    }

    public int getWeekNr() { return weekNr; }

    public void setWeekNr(int weekNr) { this.weekNr = weekNr; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpNr() { return empNr; }

    public void setEmpNr(int empNr) { this.empNr = empNr; }

    public int getPassId() {
        return passId;
    }

    public void setPassId(int passId) {
        this.passId = passId;
    }

    public int getPassNr() {
        return passNr;
    }

    public void setPassNr(int passNr) {
        this.passNr = passNr;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
