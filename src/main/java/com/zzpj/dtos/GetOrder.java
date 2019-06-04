package com.zzpj.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetOrder {

    private BookDto book;

    private GetPurchaseDto purchase;
}
