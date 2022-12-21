package com.bootcamp.rule_engine.service;

import com.bootcamp.rule_engine.dto.LoginDTO;
import com.bootcamp.rule_engine.dto.TokenDTO;
import com.bootcamp.rule_engine.model.User;
import com.bootcamp.rule_engine.repository.UserRepository;
import com.bootcamp.rule_engine.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest {

    private LoginService loginService;
    private UserRepository userRepository;
    private LoginDTO loginDTO;
    private TokenDTO tokenDTO;
    private User user;

    private User setUpUser() {
        user = new User();

        user.setEmail("admin@perficient.org");
        user.setHashedPassword("01433d49a15e192f53a6365db0f53564984c52cdeebde9aa54f12685c2627937");
        return user;
    }
    @BeforeEach
    public void init() {
       userRepository = mock(UserRepository.class);
       userRepository.save(setUpUser());

       loginService = new LoginServiceImpl(userRepository);
       loginDTO = new LoginDTO();
       loginDTO.setEmail("admin@perficient.org");
       loginDTO.setPassword("Bootcamp_2022");
       tokenDTO = new TokenDTO("tokenDTO");
    }

    @Test
    void saveUser() {
        Optional<User> tempUser = userRepository.findUserByEmail("admin@perficient.org");
        assertTrue(tempUser.isEmpty());
    }

    @Test
    void login() {
        when(loginService.login(loginDTO)).thenReturn(tokenDTO);
        assertEquals(tokenDTO.getToken(), loginService.login(loginDTO).getToken());
    }

    @Test
    void authenticatePassword() {

    }
}
