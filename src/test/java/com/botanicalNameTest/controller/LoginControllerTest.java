package com.botanicalNameTest.controller;

import com.botanicalNameTest.model.domain.User;
import com.botanicalNameTest.repository.dao.UserDao;
import com.botanicalNameTest.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean()
    private UserDao userdao;
    @MockBean
    private LoginService loginService;
    private String userJson;

    @BeforeEach
    public void setUp() {
        userJson = "{\"userId\" : \"fosong\"," +
                "\"userPassword\" : \"1356\"}";

        given(loginService.isValidLogin(new User("fosong", "1356"))).willReturn(true);
    }

    @Test
    public void loginTest() throws Exception {

        mockMvc.perform(
                post("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userJson)

        ).andExpect(status().isOk())
            .andDo(print());
    }
}
