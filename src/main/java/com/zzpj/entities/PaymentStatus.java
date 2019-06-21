package com.zzpj.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "payment_status")
@NamedQueries({
        @NamedQuery(name = "PaymentStatus.existsByName",
                query = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM PaymentStatus c WHERE c.name = :name"),
        @NamedQuery(name = "PaymentStatus.findByName", query = "SELECT c FROM PaymentStatus c WHERE c.name = :name")
})
public class PaymentStatus extends BaseEntity{

    @Column(name = "name", unique = true)
    private String name;

}
