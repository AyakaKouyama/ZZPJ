package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "login", "email", "passwordHash", "role", "userDetails", "version"})
public class UserDto {

    public Long id;

    public String login;

    public String email;

    public String passwordHash;

    public RoleDto role;

    public UserDetailsDto userDetails;

    public Long version;
}
