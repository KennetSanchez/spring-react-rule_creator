package com.bootcamp.rule_engine.service;

import com.bootcamp.rule_engine.dto.LoginDTO;
import com.bootcamp.rule_engine.dto.TokenDTO;

public interface LoginService {
    TokenDTO login(LoginDTO loginDTO);
}
