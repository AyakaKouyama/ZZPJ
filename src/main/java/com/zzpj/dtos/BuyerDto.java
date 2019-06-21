package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyerDto {

    String email;

    String phone;

    String firstName;

    String lastName;
}
