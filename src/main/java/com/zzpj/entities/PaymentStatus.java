package com.zzpj.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

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
