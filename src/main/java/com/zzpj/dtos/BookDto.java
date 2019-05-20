package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"name", "author", "category"})
public class BookDto {

    @NotEmpty(message = "name cannot be null!")
    public String name;

    @NotEmpty(message = "author cannot be null!")
    public String author;

    public CategoryDto category;
}
