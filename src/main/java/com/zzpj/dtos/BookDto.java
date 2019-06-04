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


    @NotEmpty(message = "title cannot be empty!")
    public String title;

    @NotEmpty(message = "author cannot be empty!")
    public String author;

    @NotNull(message = "price cannot be null!")
    public BigInteger price;

    public Long categoryId;

    public String isbn;

    public Integer numberOfPages;

    public String description;

}
