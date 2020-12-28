package com.example.weatherapp;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {


    @GET("locationforecast/1.9/?")
    Call<WeatherDataList> getWeatherData(@Query("lat") String lat,@Query("lon") String lon);
}
