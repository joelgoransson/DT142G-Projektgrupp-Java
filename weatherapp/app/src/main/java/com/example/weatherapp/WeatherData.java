package com.example.weatherapp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name="time", strict = false)
public class WeatherData {
    @Element(name="location")
    private String location;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
