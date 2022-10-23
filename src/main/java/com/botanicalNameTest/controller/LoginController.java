package com.botanicalNameTest.controller;

import com.botanicalNameTest.model.domain.User;
import com.botanicalNameTest.repository.dao.UserDao;
import com.botanicalNameTest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Controller
public class LoginController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        if (Objects.isNull(user)
            || Objects.isNull(user.getUserId())
            || Objects.isNull(user.getUserPassword())
            || !loginService.isValidLogin(user)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            loginService.allowUser(user);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
