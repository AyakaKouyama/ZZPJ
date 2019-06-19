package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class UserDto {

    public Long id;

    //@NotEmpty(message = "Login cannot be null!")
    public String login;

    //@NotEmpty(message = "Email cannot be empty!")
    public String email;

   // @NotEmpty(message = "Password Hash cannot be empty!")
    public String passwordHash;

   // @NotEmpty(message = "Role cannot be null!")
    public RoleDto role;

   // @NotEmpty(message = "User details cannot be null!")
    public UserDetailsDto userDetails;

    public Long version;
}
