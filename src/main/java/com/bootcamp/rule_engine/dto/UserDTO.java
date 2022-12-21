package com.bootcamp.rule_engine.dto;

import com.bootcamp.rule_engine.model.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;

    @Column(unique = true)
    @Email
    @NotBlank
    @NotNull
    private String email;


    @NotNull
    @NotBlank
    @Length(min = 3, max = 32)
    private String firstName;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 32)
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;

    private Role role;
}