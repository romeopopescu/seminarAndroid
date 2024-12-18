package com.example.seminarfromzero.utils;

import java.io.Serializable;

public class User implements Serializable {
    String username;
    int userId;

    public User(String username, int userId) {
        this.userId = userId;
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
