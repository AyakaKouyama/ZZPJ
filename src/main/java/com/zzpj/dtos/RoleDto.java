package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonPropertyOrder({"id", "name", "version"})
public class RoleDto {

    public Long id;

    public String name;

    public Long version;
}
