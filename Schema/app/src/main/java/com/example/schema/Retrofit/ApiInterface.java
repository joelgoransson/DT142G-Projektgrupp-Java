package com.example.schema.Retrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("entities.employee")
    Call<EmployeeList> getEmpList();

    @GET("entities.employeepass")
    Call<EmPassList> getEmPassList();

    @GET("entities.pass")
    Call<PassList> getPassList();

    @PUT("entities.employeepass/{id}")
    Call<ResponseBody> updateEmployeePass(@Path("id") String id, @Body EmPassObject object);
}
