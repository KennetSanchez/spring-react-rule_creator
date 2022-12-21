package com.bootcamp.rule_engine.service.impl;

import com.bootcamp.rule_engine.constant.ErrorCode;
import com.bootcamp.rule_engine.error.exception.UserError;
import com.bootcamp.rule_engine.error.exception.UserException;
import com.bootcamp.rule_engine.model.Role;
import com.bootcamp.rule_engine.model.User;
import com.bootcamp.rule_engine.repository.PermissionRepository;
import com.bootcamp.rule_engine.repository.RoleRepository;
import com.bootcamp.rule_engine.repository.UserRepository;
import com.bootcamp.rule_engine.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    public final UserRepository userRepository;
    public final RoleRepository roleRepository;
    public final PermissionRepository permissionRepository;

    @Override
    public User getUser(UUID userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public User createUser(User userDTO, UUID roleId) {
        if(!repeatedEmail(userDTO.getEmail())){
            Role role = roleRepository.findById(roleId).orElseThrow();
            userDTO.setRole(role);
            return userRepository.save(userDTO);
        }
        throw new UserException(HttpStatus.CONFLICT,  new UserError(ErrorCode.CODE_06, ErrorCode.CODE_06.getMessage()));
    }

    @Override
    public List<User> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public boolean repeatedEmail(String email){
        List<User> allUsers = getUsers();
        boolean duplicatedData = false;

        for(User user : allUsers){
            if (user.getEmail() != null && email != null && email.equals(user.getEmail())){
                duplicatedData = true;
                break;
            }
        }
        return duplicatedData;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User foundUser = userRepository.findUserByEmail(email).orElse(null);

        if(foundUser != null){
            System.out.println(foundUser);
            List<GrantedAuthority> permissions = new ArrayList<>();
            GrantedAuthority permission = new SimpleGrantedAuthority("ROLE_" + foundUser.getRole());
            permissions.add(permission);
            return new org.springframework.security.core.userdetails.User(foundUser.getEmail(), foundUser.getHashedPassword(), permissions);
        }else{
            return null;
        }
    }
}