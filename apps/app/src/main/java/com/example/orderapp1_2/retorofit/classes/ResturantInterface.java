package com.example.orderapp1_2.retorofit.classes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ResturantInterface {

    @GET("entities.menu") //länk till till API på sidan
    Call<Feed> getMenu();

    @POST("entities.menu") //länk till till API på sidan
    Call<MenuItem> createMenu(@Body MenuItem menuItem);

    @POST("entities.ordermenuitem")
    Call<Order> createOrder(@Body Order order);

    @FormUrlEncoded
    @POST("entities.ordermenuitem")
    Call<Order> createOrder(@Field("billnr") int billnr,@Field("comment") String comment,@Field("menuitemname") String menuitemname,@Field("orderitemnr") int orderitemnr ,@Field("quantity") int quantity);

}
