package com.zzpj.dtos;

import javax.validation.constraints.NotNull;

public class GetPurchaseDto {
    private Long id;

    @NotNull(message = "user cannot be null!")
    private UserDto user;

    private Long version;

    private PaymentStatusDto paymentStatus;

    @NotNull(message = "price cannot be null!")
    private double price;

    private ShippingMethodDto shippingMethod;
}
