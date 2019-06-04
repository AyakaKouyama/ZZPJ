package com.zzpj.dtos;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@Setter
@JsonPropertyOrder({"id", "name", "price"})
public class ShippingMethodDto {

    public Long id;

    @NotEmpty(message = "name cannot be null!")
    public String name;

    @NotEmpty(message = "price cannot be null!")
    public BigDecimal price;
}
