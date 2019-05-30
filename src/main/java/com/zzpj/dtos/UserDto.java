package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"login", "email", "passwordHash", "roleId", "userDetails", "version"})
public class UserDto {

    public Long id;

    public String login;

    @NotEmpty(message = "Email cannot be empty!")
    public String email;

    @NotEmpty(message = "Password Hash cannot be empty!")
    public String passwordHash;

    public RoleDto role;

    public UserDetailsDto userDetails;

    public Long version;
}
