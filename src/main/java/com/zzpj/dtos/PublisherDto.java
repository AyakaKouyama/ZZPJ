package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"name", "version" })

public class PublisherDto {

    @NotEmpty(message = "name cannot be empty!")
    private String name;

    private Long version;
}
