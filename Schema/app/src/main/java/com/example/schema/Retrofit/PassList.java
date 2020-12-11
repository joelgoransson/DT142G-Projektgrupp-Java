package com.example.schema.Retrofit;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="passes", strict=false)
public class PassList{
    @ElementList(name="pass", inline=true)
    private List<PassObject> passList;

    public List<PassObject> getPassList() {
        return passList;
    }

    public void setPassList(List<PassObject> passList) {
        this.passList = passList;
    }
}
