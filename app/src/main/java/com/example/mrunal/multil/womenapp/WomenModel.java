package com.example.mrunal.multil.womenapp;

public class WomenModel {

    public String username;
    public String name;
    public String aname;
    public String date;
    public Double mLat;
    public Double mLong;

    public WomenModel()
    {

    }

    public WomenModel(String username, String name, String aname, String date, Double mLat, Double mLong) {
        this.username = username;
        this.name = name;
        this.aname = aname;
        this.mLat = mLat;
        this.mLong = mLong;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getmLat() {
        return mLat;
    }

    public void setmLat(Double mLat) {
        this.mLat = mLat;
    }

    public Double getmLong() {
        return mLong;
    }

    public void setmLong(Double mLong) {
        this.mLong = mLong;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }
}