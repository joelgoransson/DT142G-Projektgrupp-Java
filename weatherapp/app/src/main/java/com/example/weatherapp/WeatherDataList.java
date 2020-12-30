package com.example.weatherapp;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="product", strict=false)
public class WeatherDataList {
    @ElementList(name = "time", inline = true)
    private List<WeatherData> weatherList;

    public List<WeatherData> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<WeatherData> weatherList) {
        this.weatherList = weatherList;
    }
}
