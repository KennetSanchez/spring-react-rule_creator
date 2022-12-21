package com.bootcamp.rule_engine.controller;

import com.bootcamp.rule_engine.api.UserAPI;
import com.bootcamp.rule_engine.dto.UserDTO;
import com.bootcamp.rule_engine.dto.UserPublicDTO;
import com.bootcamp.rule_engine.mapper.UserMapper;
import com.bootcamp.rule_engine.service.UserService;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

    public final UserService userService;
    public final UserMapper userMapper;

    //String uuid for the default role (user)
    private final UUID DEFAULT_ROLE = UUID.fromString("0e02ed53-f5e2-4f7a-bd86-8aadcadeb4eb");

    @Override
    public UserDTO getUser(UUID userId) {
        return userMapper.fromUser(userService.getUser(userId));
    }

    @Override
    public UserDTO createUser(@Valid UserDTO userDTO) {
        userDTO.setPassword(Hashing.sha256().hashString(userDTO.getPassword(), StandardCharsets.UTF_8).toString());
        return userMapper.fromUser(userService.createUser(userMapper.fromDTO(userDTO), DEFAULT_ROLE));
    }

    @Override
    public List<UserPublicDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::fromPublicUser).collect(Collectors.toList());
    }

}