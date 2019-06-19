package com.zzpj.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"id", "street", "streetNumber", "flatNumber", "phoneNumber", "city", "country", "firstName", "lastName", "version"})
public class UserDetailsDto {

    @NotNull
    public Long id;

    //@NotEmpty(message = "Street cannot be null!")
    public String street;

   // @NotEmpty(message = "Street number cannot be null!")
    public String streetNumber;

   // @NotEmpty(message = "Flat number cannot be null!")
    public String flatNumber;

   // @NotEmpty(message = "Phone number cannot be null!")
    public String phoneNumber;

   // @NotEmpty(message = "City cannot be null!")
    public String city;

    //@NotEmpty(message = "Country cannot be null!")
    public String country;

   // @NotEmpty(message = "First name cannot be null!")
    public String firstName;

   // @NotEmpty(message = "Last name cannot be null!")
    public String lastName;

    public Long version;
}
