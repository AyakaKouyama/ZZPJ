package com.zzpj.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetOrderedBookDto {

    Long id;

    BookDto book;

    PurchaseDto purchase;
}
