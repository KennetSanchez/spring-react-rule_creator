package com.bootcamp.rule_engine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPublicDTO {

    private UUID userId;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 32)
    private String firstName;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 32)
    private String lastName;

    @Email
    @NotBlank
    @NotNull
    private String email;

}
