package com.example.schema.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("entities.employee")
    Call<EmployeeList> getEmpList();

    @GET("entities.employeepass")
    Call<EmPassList> getEmPassList();

    @GET("entities.pass")
    Call<PassList> getPassList();
}
