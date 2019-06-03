package com.zzpj.dtos;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"book", "user", "rate"})
public class OpinionDto {

    private int rate;

    private BookDto book;

    private UserDto user;
}
