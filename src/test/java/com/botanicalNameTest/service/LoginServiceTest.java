package com.botanicalNameTest.service;

import com.botanicalNameTest.model.domain.User;
import com.botanicalNameTest.repository.dao.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(SessionLoginService.class)
@AutoConfigureMockMvc
public class LoginServiceTest {
    @MockBean
    UserDao userDao;
    @Autowired
    LoginService loginService;
    @Value("${login_user}")
    String userLogin;

    MockHttpSession session;
    MockHttpServletRequest request;

    static User validUser, notValidUser;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);

        // 체크 필요
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        validUser = new User("fosong", "1356");
        notValidUser = new User("Song", "1234");

        given(userDao.get(any(String.class))).willReturn(Optional.empty());
        given(userDao.get(validUser.getUserId())).willReturn(Optional.of(validUser));
    }

    @Test
    public void validTest() {
        assertTrue(loginService.isValidLogin(validUser));
        assertFalse(loginService.isValidLogin(notValidUser));
    }

    @Test
    public void allowUser() {
        loginService.allowUser(validUser);
        assertTrue(loginService.isLogin(validUser));
        assertFalse(loginService.isLogin(notValidUser));
        assertEquals(validUser.getUserId(), session.getAttribute(userLogin));
    }
}
