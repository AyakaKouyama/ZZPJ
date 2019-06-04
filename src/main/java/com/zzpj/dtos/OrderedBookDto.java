package com.zzpj.dtos;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"book", "purchase"})
public class OrderedBookDto {

    private Long bookId;

    private Long purchaseId;
}
