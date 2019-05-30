package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"login", "email", "passwordHash", "roleId", "userDetails", "version"})
public class UserDto {

    private String login;

    @NotEmpty(message = "Email cannot be empty!")
    private String email;

    @NotEmpty(message = "Password Hash cannot be empty!")
    private String passwordHash;

    private RoleDto roleId;

    private UserDetailsDto userDetails;

    private Long version;
}
