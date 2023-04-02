package com.example.myapplication.model;

public class Drink {
    private String key,name,image,price,description,menuId;
    private boolean isAddToCart;


    public Drink(String key, String name, String image, String price, String description) {
        this.key = key;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAddToCart() {
        return isAddToCart;
    }

    public void setAddToCart(boolean addToCart) {
        isAddToCart = addToCart;
    }
    public Drink(){

    }
}
