package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponseDto extends BaseOrderResponseDto {

    String orderId;

    StatusDto status;
}
