package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonPropertyOrder({"id", "user", "version", "paymentStatus", "price", "shippingMethod"})
public class PurchaseDto {

    private Long id;

    @NotNull(message = "user cannot be null!")
    private UserDto user;

    private Long version;

    private PaymentStatusDto paymentStatus;

    @NotNull(message = "price cannot be null!")
    private double price;

    private ShippingMethodDto shippingMethod;
}
