package com.zzpj.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "opinion")
@NamedQueries({
        @NamedQuery(name = "Opinion.findByBookId",
                query = "SELECT o FROM Opinion o WHERE o.book.id = :bookId"),
        @NamedQuery(name = "Opinion.findByUserId",
                query = "SELECT o FROM Opinion o WHERE o.user.id = :userId"),
        @NamedQuery(name = "Opinion.getAverageRateForBook",
                query = "SELECT AVG(o.rate) FROM Opinion o WHERE o.book.id = :bookId")
})
public class Opinion extends BaseEntity{

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

}
