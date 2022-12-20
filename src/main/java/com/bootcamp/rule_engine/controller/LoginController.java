package com.bootcamp.rule_engine.controller;

import com.bootcamp.rule_engine.api.LoginAPI;
import com.bootcamp.rule_engine.dto.LoginDTO;
import com.bootcamp.rule_engine.dto.TokenDTO;
import com.bootcamp.rule_engine.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController("/login")
public class LoginController implements LoginAPI {

    private final LoginService loginService;

    @Override
    public TokenDTO login(LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }
}