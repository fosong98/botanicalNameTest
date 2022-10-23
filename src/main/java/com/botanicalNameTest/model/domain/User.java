package com.botanicalNameTest.model.domain;

public class User {
    private String userId;
    private String userPassword;

    public User(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User u = (User) obj;

            return u.userId.equals(this.userId) && u.userPassword.equals(this.userPassword);
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("{\'userId\' : \'%s\', \'userPassword\' : \'%s\'}",
                userId, userPassword);
    }
}
