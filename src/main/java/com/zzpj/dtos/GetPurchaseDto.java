package com.zzpj.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class GetPurchaseDto {

    private Long id;

    @NotNull(message = "user cannot be null!")
    private UserDto user;

    private Long version;

    private PaymentStatusDto paymentStatus;

    @NotNull(message = "price cannot be null!")
    private BigDecimal price;

    private ShippingMethodDto shippingMethod;
}
