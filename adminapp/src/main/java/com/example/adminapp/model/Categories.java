package com.example.adminapp.model;

public class Categories {
    private String id , name;

    public Categories(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Categories() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
