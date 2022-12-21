package com.bootcamp.rule_engine.mapper;

import com.bootcamp.rule_engine.dto.UserDTO;
import com.bootcamp.rule_engine.dto.UserPublicDTO;
import com.bootcamp.rule_engine.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "password", target = "hashedPassword")
    User fromDTO(UserDTO userDTO);

    @Mapping(source = "hashedPassword", target = "password")
    UserDTO fromUser(User user);

    @Mapping(source = "role", target = "role")
    UserPublicDTO fromPublicUser(User user);
}
