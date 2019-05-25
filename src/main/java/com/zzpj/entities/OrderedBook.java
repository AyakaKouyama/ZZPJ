package com.zzpj.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Ordered_Book")
//@NamedQueries(@NamedQuery(name = "OrderedBook.getByBookId",
        //query = "SELECT o FROM Ordered_Book o WHERE o.book.id = :bookId"))
//@NamedQueries(@NamedQuery(name = "OrderedBook.getByPurchaseId",
        // = "SELECT o FROM Ordered_Book o WHERE o.purchase.id = :purchaseId"))
public class OrderedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id",
            foreignKey = @ForeignKey(name = "FK__book_id"))
    private Book book;

    //@ManyToOne
    //@JoinColumn(name = "purchase_id",
            //foreignKey = @ForeignKey(name = "FK__purchase_id"))
    //private Purchase purchase;
}
