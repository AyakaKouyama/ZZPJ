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
@Table(name = "publisher")
//@NamedQueries({
//        @NamedQuery(name = "Publisher.existsByName",
//                query = "SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Publisher p WHERE p.name = :name"),
//        @NamedQuery(name = "Publisher.findByName", query = "SELECT p FROM Publisher p WHERE p.name = :name")
//})

public class Publisher extends BaseEntity{



    @Column(name = "name", unique = true)
    private String name;



//    @Override
//    public Long getId() {
//        return id;
//    }
//
//    @Override
//    public Long getVersion() {
//        return version;
//    }
//
//    @Override
//    public void setVersion() {
//        this.version = getVersion() +1;
//    }
}
