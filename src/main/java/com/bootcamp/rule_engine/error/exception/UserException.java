package com.bootcamp.rule_engine.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class UserException extends RuntimeException {
    private HttpStatus httpStatus;
    private UserError error;
}
