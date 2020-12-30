package com.example.weatherapp;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitClient {
    private static String BASE_URL = "https://api.met.no/weatherapi/";
    private WeatherInterface weatherInterface;
    private static RetrofitClient INSTANCE;

    public RetrofitClient()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        weatherInterface = retrofit.create(WeatherInterface.class);
    }

    public static RetrofitClient getINSTANCE() {
        if(INSTANCE == null)
        {
            INSTANCE = new RetrofitClient();
        }

        return INSTANCE;
    }
    public Call<WeatherDataList> getWeatherData(String lat, String lon){ return weatherInterface.getWeatherData(lat,lon);}
}
