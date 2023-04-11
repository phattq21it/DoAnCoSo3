package com.example.myapplication.Interface.model;

public class CartModel {
    private  String key,name,image,price;
    private int quanlity;
    private float totalPrice;

    public CartModel() {
    }

    public CartModel(String key, String name, String image, String price, int quanlity, float totalPrice) {
        this.key = key;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quanlity = quanlity;
        this.totalPrice = totalPrice;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}

