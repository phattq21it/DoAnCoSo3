package com.example.myapplication.model;

public class Order  {
    private int ProductID;
    private String ProductName,Quantity,Price,Discount,Image;

    public Order(int ProductID, String ProductName, String Quantity, String Price, String Discount,String Image) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Discount = Discount;
        this.Image=Image;
    }

    public Order() {
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getProductId() {
        return ProductID;
    }

    public void setProductId(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String Discount) {
        this.Discount = Discount;
    }
}
