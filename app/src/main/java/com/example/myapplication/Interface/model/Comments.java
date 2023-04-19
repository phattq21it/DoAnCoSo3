package com.example.myapplication.Interface.model;

import java.util.List;

public class Comments {

    String comment,user;

    public Comments( String user, String comment) {
        this.user = user;
        this.comment = comment;
    }

    public Comments() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
