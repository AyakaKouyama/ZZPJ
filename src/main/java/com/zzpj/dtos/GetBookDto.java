package com.zzpj.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
public class GetBookDto {

    public Long id;

    public String title;

    public String author;

    public BigInteger price;

    public CategoryDto category;

    public String isbn;

    public Integer numberOfPages;

    public String description;

    public Long version;
}
