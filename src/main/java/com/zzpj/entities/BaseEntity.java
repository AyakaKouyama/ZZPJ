package com.zzpj.entities;

//public interface BaseEntity {
//    public Long getId();
//    public Long getVersion();
//    public void setVersion();
//}

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {

    @Version
    protected Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
