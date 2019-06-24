package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@JsonPropertyOrder({"id", "name", "version" })
public class PublisherDto {

    public Long id;

    @NotEmpty(message = "name cannot be empty!")
    public String name;

    public Long version;
}
