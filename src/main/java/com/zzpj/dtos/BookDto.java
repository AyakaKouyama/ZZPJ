package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"name", "author"})
public class BookDto {

    @NotNull(message = "name cannot be null!")
    public String name;

    @NotNull(message = "author cannot be null!")
    public String author;
}
