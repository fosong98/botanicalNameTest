package com.botanicalNameTest.repository.dao;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String userId) {
        super(userId + " is already exists.");
    }
}
