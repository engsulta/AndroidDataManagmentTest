package com.example.sulta.datamanagment.com.example.sulta.beans;

/**
 * Created by sulta on 3/2/2018.
 */

public class User {
    int id;
    String phoneno;
    String message;

    public User() {
    }

    public User(String phoneno, String message) {
        this.phoneno = phoneno;
        this.message = message;
    }

    public User(int id, String phoneno, String message) {
        this.id = id;
        this.phoneno = phoneno;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "user phone no:"+phoneno+" user message: "+message;
    }
}

