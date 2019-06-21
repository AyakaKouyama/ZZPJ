package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@JsonPropertyOrder({"id", "user", "version", "paymentStatus", "price", "shippingMethod"})
public class PurchaseDto {

    private Long id;

    private UserDto user;

    private Long version;

    private PaymentStatusDto paymentStatus;

    private BigDecimal price;

    private String orderId;

    @NotNull(message = "shipping method cannot be null!")
    private ShippingMethodDto shippingMethod;
}
