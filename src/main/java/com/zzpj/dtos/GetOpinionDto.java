package com.zzpj.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetOpinionDto {

    private int rate;

    private BookDto book;

    private UserDto user;

    private String description;
}
