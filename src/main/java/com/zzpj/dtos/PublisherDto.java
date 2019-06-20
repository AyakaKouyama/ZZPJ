package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@JsonPropertyOrder({"id", "name", "version" })
public class PublisherDto {

    public Long id;

    @NotEmpty(message = "name cannot be empty!")
    public String name;

    public Long version;
}
