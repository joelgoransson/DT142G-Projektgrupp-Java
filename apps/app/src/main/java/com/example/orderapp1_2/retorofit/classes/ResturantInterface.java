package com.example.orderapp1_2.retorofit.classes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ResturantInterface {

    @GET("entities.menuitem") //länk till till API på sidan
    Call<MenuItemList> getMenu();

    @POST("entities.menuitem") //länk till till API på sidan
    Call<MenuItem> createMenuItem(@Body MenuItem menuItem);

    @POST("entities.orders") //länk till till API på sidan
    Call<Orders> createOrdersItem(@Body Orders orderItem);

    @GET("entities.orders") //länk till till API på sidan
    Call<OrdersList> getOrders();


    @POST("entities.ordermenuitem") //länk till till API på sidan
    Call<Order> createOrder(@Body Order order);

    @GET("entities.ordermenuitem") //länk till till API på sidan
    Call<OrderList> getOrderItem();


    @POST("entities.bill") //länk till till API på sidan
    Call<Bill> createBill(@Body Bill bill);

    @GET("entities.bill") //länk till till API på sidan
    Call<BillList> getBill();

    @PUT("entities.bill/{id}")
    Call<Bill> updateBIll(@Path("id") String id, @Body Bill billupdater);
}
