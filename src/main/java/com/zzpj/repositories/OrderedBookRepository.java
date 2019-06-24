package com.zzpj.repositories;

import com.zzpj.entities.OrderedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedBookRepository extends JpaRepository<OrderedBook, Long> {
    List<OrderedBook> findAll();

    OrderedBook save(OrderedBook ob);

    List<OrderedBook> findByBookId(Long bookId);

    List<OrderedBook> findByPurchaseId(Long purchaseId);
}
