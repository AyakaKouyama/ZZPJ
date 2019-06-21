package com.zzpj.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "shipping_method")
@NamedQueries({
        @NamedQuery(name = "ShippingMethod.existsByName",
                query = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ShippingMethod c WHERE c.name = :name"),
        @NamedQuery(name = "ShippingMethod.findByName", query = "SELECT c FROM ShippingMethod c WHERE c.name = :name")
})


public class ShippingMethod extends BaseEntity{

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

}
