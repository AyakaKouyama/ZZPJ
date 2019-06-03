package com.zzpj.services.interfaces;

import com.zzpj.entities.OrderedBook;
import com.zzpj.repositories.OrderedBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderedBookService extends BaseService<OrderedBookRepository, OrderedBook> {
    List<OrderedBook> findAll();

    OrderedBook add(OrderedBook orderedBook);

    OrderedBook update(OrderedBook orderedBook);


    //List<OrderedBook> getByBookId(int bookId);

    //List<OrderedBook> getByUserId(int userId);
}
