package com.example.schema.Threads;

import com.example.schema.Retrofit.ApiInterface;
import com.example.schema.Retrofit.EmployeeList;
import com.example.schema.Retrofit.EmployeeObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ReadThread implements Runnable {
    private static final String BASE_URL="http://192.168.0.37:8080/Hemsida/webresources/";
    List<EmployeeObject> employeeList;
    @Override
    public void run() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<EmployeeList> empCall = apiInterface.getEmpList();
        try {
            employeeList = empCall.execute().body().getEmployeeList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
