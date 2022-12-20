package com.bootcamp.rule_engine.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    CODE_00("Restart your pc, we couldn't track the error."),
    CODE_01("The password doesn't fulfill the character diversity"),
    CODE_02("The email doesn't belong to the expected domain."),
    CODE_04("The name can take only letters."),
    CODE_05("The last name can take only letters."),
    CODE_06("The email its a unique value. Try with another value"),
    CODE_07("The password it's not the expected one. Try again"),
    CODE_08("Unauthorized. In order to filter the records you need to be logged."),
    CODE_09("Unauthorized. Due to security reasons, you can only get the information of yourself"),
    CODE_11("User not found");

    private final String message;
}
