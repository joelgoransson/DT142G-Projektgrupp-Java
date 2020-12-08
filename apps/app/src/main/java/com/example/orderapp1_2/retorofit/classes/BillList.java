package com.example.orderapp1_2.retorofit.classes;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="bills", strict=false)
public class BillList {
    @ElementList(name = "bill", inline=true)
    private List<Bill> billList;

    public List<Bill> getBillList() {
        return billList;
    }

    public void setMenuList(List<Bill> billList) {
        this.billList = billList;
    }
}
