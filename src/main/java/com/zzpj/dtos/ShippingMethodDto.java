package com.zzpj.dtos;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@JsonPropertyOrder({"id", "name", "price"})
public class ShippingMethodDto {

    @NotEmpty(message = "name cannot be null!")
    public Long id;

    @NotEmpty(message = "name cannot be null!")
    public String name;

    @NotEmpty(message = "name cannot be null!")
    public Double price;
}
