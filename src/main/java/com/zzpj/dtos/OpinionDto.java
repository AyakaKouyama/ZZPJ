package com.zzpj.dtos;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"bookId", "userId", "rate", "description"})
public class OpinionDto {

    private int rate;

    private Long bookId;

    private Long userId;

    private String description;
}
