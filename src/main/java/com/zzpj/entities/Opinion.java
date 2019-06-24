package com.zzpj.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "opinion")
@NamedQueries({
        @NamedQuery(name = "Opinion.findByBookId",
                query = "SELECT o FROM Opinion o WHERE o.book.id = :bookId ORDER BY o.date"),
        @NamedQuery(name = "Opinion.findByUserId",
                query = "SELECT o FROM Opinion o WHERE o.user.id = :userId ORDER BY o.date"),
        @NamedQuery(name = "Opinion.getAverageRateForBook",
                query = "SELECT AVG(o.rate) FROM Opinion o WHERE o.book.id = :bookId")
})
public class Opinion extends BaseEntity {

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

    @Column(name = "date")
    private LocalTime date;
}
