package com.example.weatherapp;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherInterface {


    @Headers({
            "User-agent: https://github.com/joelgoransson/DT142G-Projektgrupp-Java"
    })

    @GET("locationforecast/2.0/classic?")
    Call<WeatherDataList> getWeatherData(@Query("lat") String lat,@Query("lon") String lon);
}
