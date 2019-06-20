package com.zzpj.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@MappedSuperclass
public class BaseEntity {

    @Version
    protected Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

}
