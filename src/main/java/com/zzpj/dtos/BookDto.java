package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"title", "author", "price", "category", "isbn", "numberOfPages", "description", "version"})
public class BookDto {

    public Long id;

    @NotEmpty(message = "title cannot be empty!")
    public String title;

    @NotEmpty(message = "author cannot be empty!")
    public String author;

    @NotNull(message = "price cannot be null!")
    private BigInteger price;

    public CategoryDto category;

    private String isbn;

    private Integer numberOfPages;

    private String description;

    private Long version;
}
