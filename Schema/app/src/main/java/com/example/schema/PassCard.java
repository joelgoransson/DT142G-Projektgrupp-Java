package com.example.schema;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PassCard{
    private int id;
    private int passId;
    private int passNr;
    private int weekday;
    private String empName;
    private int weekNr;

    @Override
    public String toString() {
        return "PassCard{" +
                "id=" + id +
                ", passId=" + passId +
                ", passNr=" + passNr +
                ", weekday=" + weekday +
                ", weeknr=" + weekNr +
                ", empName='" + empName + '\'' +
                '}';
    }

    public PassCard(){}

    public PassCard(int id, int passId, int passNr, int weekday, String empName, int weekNr) {
        this.id = id;
        this.passId = passId;
        this.passNr = passNr;
        this.weekday = weekday;
        this.empName = empName;
        this.weekNr = weekNr;
    }

    public int getWeekNr() { return weekNr; }

    public void setWeekNr(int weekNr) { this.weekNr = weekNr; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
