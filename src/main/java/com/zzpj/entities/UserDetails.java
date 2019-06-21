package com.zzpj.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "userDetails")
//@NamedQueries({
//        @NamedQuery(name = "UserDetails.existsByName",
//                query = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM UserDetails ud WHERE ud.id = :id"),
//        @NamedQuery(name = "UserDetails.findByName", query = "SELECT ud FROM UserDetails ud WHERE ud.id = :id")
//})

public class UserDetails extends BaseEntity{

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "street_number", nullable = false)
    private String streetNumber;

    @Column(name = "flat_number", nullable = false)
    private String flatNumber;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

}
