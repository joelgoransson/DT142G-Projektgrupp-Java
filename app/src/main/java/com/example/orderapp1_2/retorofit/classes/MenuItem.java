package com.example.orderapp1_2.retorofit.classes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Klass sturktur för hur XML filen ska parsas, används av Feed
 * Speciella notationer för databasen och hur den ska läsas
 * Dokumentation för notationer för SimpleXML converter: http://simple.sourceforge.net/download/stream/doc/tutorial/tutorial.php
 */

@Root(name="menu", strict = false) //Namnet på objekten i API, strict=false betyder att den inte throwar om den hittar ett XML element som inte kan mappas till klassen
public class MenuItem {
    @Element(name = "name") //Namnet på det elementet i API
    private String name;

    @Element(name = "price")
    private String price;

    @Element(name = "type")
    private String type;

    public MenuItem(){
    }

    public MenuItem(@Element(name="name") String name, @Element(name="price") String price, @Element(name="type") String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
