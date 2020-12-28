package com.example.weatherapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private RetrofitClient retrofitClient;
private List<WeatherData> weatherDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWeatherData();
    }




    private void getWeatherData(){
        //lat=60.10;lon=9.58
        String lat = "60.10";
        String lon = "9.58";
        retrofitClient = RetrofitClient.getINSTANCE();
        Call<WeatherDataList> call = retrofitClient.getWeatherData(lat,lon);

        call.enqueue(new Callback<WeatherDataList>() {
            @Override
            public void onResponse(Call<WeatherDataList> call, Response<WeatherDataList> response) {
                Log.d("Conneciton succes", response.message());
                weatherDataList = response.body().getWeatherList();

            }

            @Override
            public void onFailure(Call<WeatherDataList> call, Throwable t) {
                Log.d("Conneciton failed", t.getMessage());
            }
        });

    }
}


