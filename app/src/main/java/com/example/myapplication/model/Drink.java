package com.example.myapplication.model;

public class Drink {
    private String key,name,image,price,description;
    private boolean isAddToCart;



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
    public boolean isAddToCart() {
        return isAddToCart;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddToCart(boolean addToCart) {
        isAddToCart = addToCart;
    }
    public Drink(){

    }
}
