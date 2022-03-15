package com.example.diary.model;

public class UserSingleton {

    private static UserSingleton instance;

    private String email;

    private UserSingleton() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static UserSingleton getInstance() {
        if (instance == null) {
            instance = new UserSingleton();
        }
        return instance;
    }
}
