package com.zzpj.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Data
@NoArgsConstructor
@Entity
@Table(name = "opinion")
//@NamedQueries({
//        @NamedQuery(name = "Opinion.getByBookId",
//                query = "SELECT o FROM Opinion o WHERE o.book.id = :bookId"),
//        @NamedQuery(name = "Opinion.getByUserId",
//                query = "SELECT o FROM Opinion o WHERE o.user.id = :userId")
//})
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rate")
    private int rate;


    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "book_id",
            foreignKey = @ForeignKey(name = "FK__book_id"))
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "FK__user_id"))
    private User user;

    @Version
    private Long version;
}
