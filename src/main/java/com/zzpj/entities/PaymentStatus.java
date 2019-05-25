package com.zzpj.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PaymentStatus")
@NamedQueries({
        @NamedQuery(name = "PaymentStatus.existsByName",
                query = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM PaymentStatus c WHERE c.name = :name"),
        @NamedQuery(name = "PaymentStatus.findByName", query = "SELECT c FROM PaymentStatus c WHERE c.name = :name")
})
public class PaymentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Version
    private Long version;
}
