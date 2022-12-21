package com.bootcamp.rule_engine.api;

import com.bootcamp.rule_engine.dto.UserDTO;
import com.bootcamp.rule_engine.dto.UserPublicDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000, http://54.89.157.150:3000")
@RequestMapping("/users")
public interface UserAPI {

    @GetMapping("/{userId}")
    UserDTO getUser(@PathVariable UUID userId);

    @PostMapping("/create-user")
    UserDTO createUser(@RequestBody @Valid UserDTO userDTO);

    @GetMapping
    List<UserPublicDTO> getUsers();
}
