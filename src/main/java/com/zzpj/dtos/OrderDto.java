package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    String continueUrl;

    String notifyUrl;

    String customerIp;

    String merchantPosId;

    String description;

    String currencyCode;

    String totalAmount;

    List<ProductDto> products;

    BuyerDto buyer;

    String status;
}
