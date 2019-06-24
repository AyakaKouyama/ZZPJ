package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@JsonPropertyOrder({"title", "author", "price", "category", "isbn", "numberOfPages", "description", "version"})
public class BookDto {

    public Long id;

    @NotEmpty(message = "title cannot be empty!")
    public String title;

    @NotEmpty(message = "author cannot be empty!")
    public String author;

    @NotNull(message = "price cannot be null!")
    private BigDecimal price;

    public CategoryDto category;

    public PublisherDto publisher;

    private String isbn;

    private Integer numberOfPages;

    private String description;

    private Long version;
}
