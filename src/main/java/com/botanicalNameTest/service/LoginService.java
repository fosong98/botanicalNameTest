package com.botanicalNameTest.service;

import com.botanicalNameTest.model.domain.User;

public interface LoginService {
    public boolean isValidLogin(User user);
    public boolean isLogin(User user);
    public void allowUser(User user);
}
