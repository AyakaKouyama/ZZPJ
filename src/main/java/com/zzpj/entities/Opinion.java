package com.zzpj.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
