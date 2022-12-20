package com.bootcamp.rule_engine.service.impl;

import com.bootcamp.rule_engine.constant.ErrorCode;
import com.bootcamp.rule_engine.dto.LoginDTO;
import com.bootcamp.rule_engine.dto.TokenDTO;
import com.bootcamp.rule_engine.error.exception.UserError;
import com.bootcamp.rule_engine.error.exception.UserException;
import com.bootcamp.rule_engine.model.User;
import com.bootcamp.rule_engine.repository.UserRepository;
import com.bootcamp.rule_engine.service.LoginService;
import com.bootcamp.rule_engine.utils.JWTParser;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    public final UserRepository repository;
    private final static long EXPIRATION_TOKEN_TIME = 1000000L;

    @Override
    public TokenDTO login(LoginDTO loginDTO) {
        User user = StreamSupport.stream(repository.findAll().spliterator(), false)
                .filter(currentUser -> currentUser.getEmail().equals(loginDTO.getEmail()))
                .findFirst()
                .orElse(null);
        if(user!= null){
            return authenticatePassword(user, loginDTO);
        }else{
            throw new UserException(HttpStatus.NOT_ACCEPTABLE, new UserError(ErrorCode.CODE_11, ErrorCode.CODE_11.getMessage()));
        }
    }

    @SneakyThrows
    private TokenDTO authenticatePassword(User user, LoginDTO loginDTO) {
        String expectedHash = user.getHashedPassword();
        String requestHash  = Hashing.sha256().hashString(loginDTO.getPassword(), StandardCharsets.UTF_8).toString();
        if (requestHash.equals(expectedHash)) {
            Map<String, String> claims = new HashMap<>();
            claims.put("userId", user.getId().toString());
            claims.put("userRole", user.getRole().getName().toString());
            return new TokenDTO(JWTParser.createJWT(user.getId().toString(), user.getFirstName(), user.getLastName(), claims, EXPIRATION_TOKEN_TIME));
        }
        throw new UserException(HttpStatus.ACCEPTED, new UserError(ErrorCode.CODE_07, ErrorCode.CODE_07.getMessage()));
    }
}
