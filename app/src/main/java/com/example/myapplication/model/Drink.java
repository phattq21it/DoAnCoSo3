package com.example.myapplication.model;

public class Drink {
    private String key,name,image,price,discount,description,menuID;
    private boolean isAddToCart;


    public Drink(String key, String name, String image, String price, String discount, String description, String menuID, boolean isAddToCart) {
        this.key = key;
        this.name = name;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.menuID = menuID;
        this.isAddToCart = isAddToCart;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
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

    public void setAddToCart(boolean addToCart) {
        isAddToCart = addToCart;
    }
    public Drink(){

    }
}
