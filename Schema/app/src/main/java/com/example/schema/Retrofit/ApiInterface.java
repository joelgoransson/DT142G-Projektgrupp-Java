package com.example.schema.Retrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("entities.employee")
    Call<EmployeeList> getEmpList();

    @GET("entities.employeepass")
    Call<EmPassList> getEmPassList();

    @GET("entities.employeepass/employee/{employeename}")
    Call<EmPassList> getEmpPassByName(@Path("employeename") String name);

    @PUT("entities.employeepass/{id}")
    Call<ResponseBody> updateEmployeePass(@Path("id") Integer id, @Body EmPassObject object);

    @POST("entities.employeepass/post")
    Call<ResponseBody> createEmployeePassAutoID(@Body EmPassObject object);

    @DELETE("entities.employeepass/{id}")
    Call<ResponseBody> deleteEmployeePass(@Path("id") Integer id);

    @GET("entities.pass")
    Call<PassList> getPassList();

    @GET("entities.pass/pass/{passnr}/Day/{day}/Week/{week}")
    Call<PassObject> findPass(@Path("passnr") Integer passnr, @Path("day") Integer day, @Path("week") Integer week);

    @PUT("entities.pass/{id}")
    Call<ResponseBody> updatePass(@Path("id") Integer id, @Body PassObject object);

    @POST("entities.pass/post")
    Call<PassObject> createPass(@Body PassObject object);

    @GET("entities.pass/{id}")
    Call<PassObject> getPass(@Path("id") Integer id);


}
