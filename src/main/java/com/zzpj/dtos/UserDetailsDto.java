package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"street", "streetNumber", "flatNumber", "phoneNumber", "city", "country", "firstName", "lastName", "version"})
public class UserDetailsDto {

    private String street;

    private String streetNumber;

    private String flatNumber;

    private Long phoneNumber;

    private String city;

    private String country;

    @NotNull(message = "First name cannot be null!")
    private String firstName;

    @NotNull(message = "Last name cannot be null!")
    private String lastName;

    private Long version;
}
