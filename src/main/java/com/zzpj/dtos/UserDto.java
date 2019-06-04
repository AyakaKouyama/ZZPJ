package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"id", "login", "email", "passwordHash", "role", "userDetails", "version"})
public class UserDto {

    public Long id;

    @NotEmpty(message = "Login cannot be null!")
    public String login;

    @NotEmpty(message = "Email cannot be empty!")
    public String email;

    public String passwordHash;

    @NotNull(message = "Role cannot be null!")
    public Long roleId;

    public UserDetailsDto userDetails;

    public Long version;
}
