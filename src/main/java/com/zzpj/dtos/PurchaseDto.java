package com.zzpj.dtos;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"user", "version", "paymentStatus", "price", "shippingMethod"})
public class PurchaseDto {

    @NotNull(message = "user cannot be null!")
    private Long userId;

    private Long paymentStatusId;

    @NotNull(message = "price cannot be null!")
    private BigDecimal price;

    private Long shippingMethodId;
}
