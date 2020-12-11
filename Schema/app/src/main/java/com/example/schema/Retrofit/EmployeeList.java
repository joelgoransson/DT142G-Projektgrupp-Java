package com.example.schema.Retrofit;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="employees", strict=false)
public class EmployeeList{
    @ElementList(name="employee", inline=true)
    private List<EmployeeObject> employeeList;

    public List<EmployeeObject> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeObject> employeeList) {
        this.employeeList = employeeList;
    }
}