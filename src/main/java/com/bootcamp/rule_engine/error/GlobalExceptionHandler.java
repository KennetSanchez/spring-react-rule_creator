package com.bootcamp.rule_engine.error;

import com.bootcamp.rule_engine.constant.ErrorCode;
import com.bootcamp.rule_engine.error.exception.UserError;
import com.bootcamp.rule_engine.error.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserError> handleException(UserException userException){
        return new ResponseEntity<>(userException.getError(), userException.getHttpStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<UserError> handleArgumentException(MethodArgumentNotValidException exception){
        BindingResult binding = exception.getBindingResult();

        String wrongField = Objects.requireNonNull(binding.getFieldError()).getField();

        Map<String, Object> body = getErrorCode(wrongField);

        ErrorCode errorCode = (ErrorCode) body.get("errorName");
        String errorMsg = body.get("errorMsg").toString();
        HttpStatus errorStatus = HttpStatus.BAD_REQUEST;

        UserError userError = new UserError(errorCode, errorMsg);
        return new ResponseEntity<>(userError, errorStatus);
    }

    private Map<String, Object> getErrorCode(String wrongField) {
        Map<String, Object> errorInfo = new HashMap<>();

        ErrorCode errorName = ErrorCode.CODE_00;
        String errorMsg = ErrorCode.CODE_00.getMessage();

        switch (wrongField){
            case "password":
                errorName = ErrorCode.CODE_01;
                errorMsg = ErrorCode.CODE_01.getMessage();
                break;

            case "email":
                errorName = ErrorCode.CODE_02;
                errorMsg = ErrorCode.CODE_02.getMessage();
                break;

            case "firstName":
                errorName = ErrorCode.CODE_04;
                errorMsg = ErrorCode.CODE_04.getMessage();
                break;

            case "lastName":
                errorName = ErrorCode.CODE_05;
                errorMsg = ErrorCode.CODE_05.getMessage();
                break;
        }
        errorInfo.put("errorName", errorName);
        errorInfo.put("errorMsg", errorMsg);

        return errorInfo;
    }


}
