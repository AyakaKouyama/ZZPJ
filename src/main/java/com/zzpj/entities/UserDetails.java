package com.zzpj.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "userDetails")
@NamedQueries({
        @NamedQuery(name = "UserDetails.existsByName",
                query = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM UserDetails ud WHERE ud.id = :id"),
        @NamedQuery(name = "UserDetails.findByName", query = "SELECT ud FROM UserDetails ud WHERE ud.id = :id")
})

public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "streetNumber")
    private String streetNumber;

    @Column(name = "flatNumber")
    private String flatNumber;

    @Column(name = "phoneNumber")
    private Long phoneNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Version
    private Long version;

}
