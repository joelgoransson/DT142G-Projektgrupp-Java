package com.example.weatherapp;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="weatherdata", strict=false)
public class WeatherDataList {
    @Element(name="product")
    private Product product;

    @Root(name="product", strict=false)
    static class Product{

        @Attribute(name = "class")
        private String classs;

        @ElementList(inline=true, required = false)
        private List<WeatherData> weatherList;
    }





}
