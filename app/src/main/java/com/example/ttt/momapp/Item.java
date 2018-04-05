package com.example.ttt.momapp;

/**
 * Created by Bharat on 4/2/18.
 */

public class Item {
    private String name, location, category, expiration, misc;
    private int itemCount;
    private double price;
    public Item(String name, int itemCount, String location, double price, String category, String expiration, String misc){
        this.name = name;
        this.itemCount = itemCount;
        this.location = location;
        this.price = price;
        this.category = category;
        this.expiration = expiration;
        this.misc = misc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


