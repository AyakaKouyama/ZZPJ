package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@JsonPropertyOrder({"id", "name"})
public class PaymentStatusDto {

    @NotEmpty(message = "name cannot be null!")
    public Long id;

    @NotEmpty(message = "name cannot be null!")
    public String name;
}
