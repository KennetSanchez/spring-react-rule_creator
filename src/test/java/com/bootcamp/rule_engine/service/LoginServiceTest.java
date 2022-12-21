package com.bootcamp.rule_engine.service;

import com.bootcamp.rule_engine.dto.LoginDTO;
import com.bootcamp.rule_engine.dto.TokenDTO;
import com.bootcamp.rule_engine.repository.UserRepository;
import com.bootcamp.rule_engine.service.impl.LoginServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest {

    private LoginService loginService;
    private ObjectMapper objectMapper;
    private UserRepository userRepository;
    private LoginDTO loginDTO;
    private TokenDTO tokenDTO;

    @BeforeEach
    public void init() {
       objectMapper = new ObjectMapper();
       loginService = new LoginServiceImpl(userRepository);
       loginDTO = new LoginDTO();
       tokenDTO = new TokenDTO("");
       loginDTO.setEmail("admin@perficient.org");
       loginDTO.setPassword("Bootcamp_2022");
    }

    @Test
    void login() {
        when(loginService.login(loginDTO)).thenReturn(tokenDTO);
    }
}
