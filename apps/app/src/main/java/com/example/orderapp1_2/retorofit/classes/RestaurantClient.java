package com.example.orderapp1_2.retorofit.classes;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RestaurantClient {
    private static final String BASE_URL = "http://192.168.1.4:8080/Hemsida/webresources/";
    private ResturantInterface resturantInterface;
    private static RestaurantClient INSTANCE;

    public RestaurantClient()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        resturantInterface = retrofit.create(ResturantInterface.class);
    }

    public static RestaurantClient getINSTANCE() {
        if(INSTANCE == null)
        {
            INSTANCE = new RestaurantClient();
        }
        return INSTANCE;
    }
    public Call<OrdersList> getOrders()
    {
        return resturantInterface.getOrders();
    }
    public Call<MenuItemList> getMenu()
    {
        return resturantInterface.getMenu();
    }
    public Call<OrderList> getOrder()
    {
        return resturantInterface.getOrderItem();
    }
    public Call<BillList> getBill()
    {
        return resturantInterface.getBill();
    }
    public Call<Order> createOrder(Order order){return resturantInterface.createOrder(order);}
    public Call<Bill> createBill(Bill bill){return resturantInterface.createBill(bill);}
    public Call<MenuItem> createMenuItem(MenuItem menuItem){return resturantInterface.createMenuItem(menuItem);}
    public Call<Orders> createOrdersItem(Orders item){return resturantInterface.createOrdersItem(item);}
    public Call<Bill> updateBill(String id, Bill billupdater){return resturantInterface.updateBIll(id,billupdater);}


}
