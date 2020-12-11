package com.example.schema.Retrofit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="employee", strict=false)
public class EmployeeObject {
    @Element(name="name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
