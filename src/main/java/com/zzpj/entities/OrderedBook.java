package com.zzpj.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "Ordered_Book")
//@NamedQueries({
//        @NamedQuery(name = "OrderedBook.getByBookId",
//                query = "SELECT o FROM Ordered_Book o WHERE o.book.id = :bookId"),
//        @NamedQuery(name = "OrderedBook.getByPurchaseId",
//                query = "SELECT o FROM Ordered_Book o WHERE o.purchase.id = :purchaseId")
//})
public class OrderedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id",
            foreignKey = @ForeignKey(name = "FK__book_id"))
    private Book book;

    @ManyToOne
    @JoinColumn(name = "purchase_id",
            foreignKey = @ForeignKey(name = "FK__purchase_id"))
    private Purchase purchase;

    @Version
    private Long version;
}
