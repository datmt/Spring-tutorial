package com.datmt.spring.springexceptionhandling.model;


public class User {
    private String name;
    private String email;
    public User(String username, String email) {
        this.name = username;
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
