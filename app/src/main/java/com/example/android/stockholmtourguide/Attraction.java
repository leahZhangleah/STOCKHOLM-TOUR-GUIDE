package com.example.android.stockholmtourguide;

import android.os.Parcel;
import android.os.Parcelable;

public class Attraction{
    private int phtoId,rating;
    private String name, introduction, phone, email, address, openTime, website,price;

    public Attraction(int phtoId, int rating, String name, String introduction, String phone, String email, String address, String openTime, String website, String price) {
        this.phtoId = phtoId;
        this.rating = rating;
        this.name = name;
        this.introduction = introduction;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.openTime = openTime;
        this.website = website;
        this.price = price;
    }

    public int getPhtoId() {
        return phtoId;
    }

    public int getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getWebsite() {
        return website;
    }

    public String getPrice() {
        return price;
    }

}
