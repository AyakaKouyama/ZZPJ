package com.zzpj.dtos;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"id", "user", "version", "paymentStatus", "price", "shippingMethod"})
public class PurchaseDto {

    private Long id;

    @NotNull(message = "user cannot be null!")
    private Long userId;

    private Long version;

    private Long paymentStatusId;

    @NotNull(message = "price cannot be null!")
    private double price;

    private Long shippingMethodId;
}
