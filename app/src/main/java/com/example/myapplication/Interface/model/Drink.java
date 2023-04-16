package com.example.myapplication.Interface.model;

public class Drink {
    private String key;
    private String name;
    private String image;
    private String price;
    private String description;
    private String discount;

    private int quantityPurchased;
    private boolean isAddToCart;
    public int getquantityPurchased() {
        return quantityPurchased;
    }

    public void setquantityPurchased(int soluotban) {
        this.quantityPurchased = soluotban;
    }



    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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
