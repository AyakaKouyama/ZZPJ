package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "book", "purchase"})
public class OrderedBookDto {

    private Long id;

    private BookDto book;

    private PurchaseDto purchase;
}
