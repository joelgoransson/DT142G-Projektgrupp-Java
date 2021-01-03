package com.example.weatherapp;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;


@Element(name="time")
public class WeatherData {
    @Element(name="location")
    private Location location;


    public class Location{
        @Attribute(name = "altitude")
        private String altitude;

        public Location(@Attribute(name = "altitude") String altitude ){
            this.altitude= altitude;
        }
    }


}
