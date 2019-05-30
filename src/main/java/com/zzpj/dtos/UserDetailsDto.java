package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"street", "streetNumber", "flatNumber", "phoneNumber", "city", "country", "firstName", "lastName", "version"})
public class UserDetailsDto {

    public Long id;

    public String street;

    public String streetNumber;

    public String flatNumber;

    public Long phoneNumber;

    public String city;

    public String country;

    @NotNull(message = "First name cannot be null!")
    public String firstName;

    @NotNull(message = "Last name cannot be null!")
    public String lastName;

    public Long version;
}
