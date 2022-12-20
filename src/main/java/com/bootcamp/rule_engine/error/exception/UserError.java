package com.bootcamp.rule_engine.error.exception;

import com.bootcamp.rule_engine.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserError {
    private ErrorCode code;
    private String message;
}
