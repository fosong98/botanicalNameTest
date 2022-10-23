package com.botanicalNameTest.service;

import com.botanicalNameTest.model.domain.User;
import com.botanicalNameTest.repository.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class SessionLoginService implements LoginService {
    @Autowired
    UserDao userDao;
    @Autowired
    HttpSession session;
    @Value("${login_user}")
    String userLogin;


    public boolean isValidLogin(User user) {
        if (user == null)
            return false;
        Optional<User> optionalUser = userDao.get(user.getUserId());

        return (!optionalUser.isEmpty() && optionalUser.get().equals(user));
    }

    public boolean isLogin(User user) {
        return session.getAttribute(userLogin) == user.getUserId();
    }

    public void allowUser(User user) {
        session.setAttribute(userLogin,  user.getUserId());
    }
}
