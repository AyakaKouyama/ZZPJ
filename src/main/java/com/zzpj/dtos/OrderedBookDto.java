package com.zzpj.dtos;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"id", "book", "purchase"})
public class OrderedBookDto {

    private Long id;

    private BookDto book;

    private PurchaseDto purchase;
}
