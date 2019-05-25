package com.zzpj.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ShippingMethod")
@NamedQueries({
        @NamedQuery(name = "ShippingMethod.existsByName",
                query = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ShippingMethod c WHERE c.name = :name"),
        @NamedQuery(name = "ShippingMethod.findByName", query = "SELECT c FROM ShippingMethod c WHERE c.name = :name")
})


public class ShippingMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "price")
    private double price;

    @Version
    private Long version;
}
