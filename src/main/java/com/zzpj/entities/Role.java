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
@Table(name = "role")
@NamedQueries({
        @NamedQuery(name = "Role.existsByName",
                query = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Role r WHERE r.name = :name"),
        @NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name")
})

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Version
    private Long version;
}
