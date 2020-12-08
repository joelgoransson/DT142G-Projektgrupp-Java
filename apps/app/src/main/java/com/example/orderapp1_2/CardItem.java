package com.example.orderapp1_2;

public class CardItem {
    private String bordTV;
    private String infoTV;

    public CardItem(String bordTV, String infoTV)
    {
        this.bordTV = bordTV;
        this.infoTV = infoTV;
    }

    public String getBordTV() {
        return bordTV;
    }

    public void setBordTV(String bordTV) {
        this.bordTV = bordTV;
    }

    public String getInfoTV() {
        return infoTV;
    }

    public void setInfoTV(String infoTV) {
        this.infoTV = infoTV;
    }
}
