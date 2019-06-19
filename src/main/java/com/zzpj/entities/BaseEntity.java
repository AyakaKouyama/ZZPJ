package com.zzpj.entities;

//public interface BaseEntity {
//    public Long getId();
//    public Long getVersion();
//    public void setVersion();
//}

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
