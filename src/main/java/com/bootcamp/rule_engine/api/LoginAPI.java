package com.bootcamp.rule_engine.api;


import com.bootcamp.rule_engine.dto.LoginDTO;
import com.bootcamp.rule_engine.dto.TokenDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/login")
public interface LoginAPI {

    @PostMapping()
    TokenDTO login(@RequestBody @Valid LoginDTO loginDTO);

}
