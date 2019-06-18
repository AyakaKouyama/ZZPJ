package com.zzpj.repositories;

import com.zzpj.entities.OrderedBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedBookRepository extends CrudRepository<OrderedBook, Long> {
    List<OrderedBook> findAll();

    OrderedBook save(OrderedBook ob);

    List<OrderedBook> findByBookId(Long bookId);

    List<OrderedBook> findByPurchaseId(Long purchaseId);
}
