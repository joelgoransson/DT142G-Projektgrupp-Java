package com.example.schema.Retrofit;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="employeepasses", strict=false)
public class EmPassList{
    @ElementList(name="employeepass", inline=true)
    private List<EmPassObject> emPassList;

    public List<EmPassObject> getEmPassList() {
        return emPassList;
    }

    public void setEmPassList(List<EmPassObject> emPassList) {
        this.emPassList = emPassList;
    }
}
