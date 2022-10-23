package com.botanicalNameTest.repository.dao;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super(userId + " is not found.");
    }
}
