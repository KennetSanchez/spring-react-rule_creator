package com.bootcamp.rule_engine.service;

import com.bootcamp.rule_engine.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User getUser(@PathVariable UUID userId);

    User createUser(@RequestBody User userDTO, UUID rolId);

    List<User> getUsers();
}
