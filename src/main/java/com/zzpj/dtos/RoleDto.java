package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"id", "name", "version"})
public class RoleDto {

    public Long id;

    @NotEmpty(message = "Name cannot be null!")
    public String name;

    public Long version;
}
