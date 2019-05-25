package com.zzpj.dtos;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "name", "price"})
public class ShippingMethodDto {

    public Long id;
    public String name;
    public Double price;
}
