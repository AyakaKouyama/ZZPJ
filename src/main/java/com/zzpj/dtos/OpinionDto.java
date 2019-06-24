package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@JsonPropertyOrder({"id", "book", "user", "rate"})
public class OpinionDto {

    private Long id;

    @Max(value = 5)
    @Min(value = 1)
    private int rate;

    private BookDto book;

    private UserDto user;
}
