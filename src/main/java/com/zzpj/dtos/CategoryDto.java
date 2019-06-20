package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@JsonPropertyOrder({"id", "name", "version"})
public class CategoryDto {

    public Long id;

    public String name;

    public Long version;
}
