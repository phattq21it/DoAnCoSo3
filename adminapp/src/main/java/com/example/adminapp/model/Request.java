package com.example.adminapp.model;


public class Request {
    private String key,name,address, total,time;
    long currentTimeMillis;


    public Request() {
    }



    public Request(String key, String name, String address, String total, String time, long currentTimeMillis) {
        this.key = key;
        this.name = name;
        this.address = address;
        this.total = total;
        this.time = time;
        this.currentTimeMillis = currentTimeMillis;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    public long getCurrentTimeMillis() {
        return currentTimeMillis;
    }

    public void setCurrentTimeMillis(long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }


}
