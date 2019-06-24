package com.zzpj.services.interfaces;

import com.zzpj.dtos.OrderedBookDto;
import com.zzpj.entities.OrderedBook;

import java.util.List;

public interface OrderedBookService extends BaseService<OrderedBook, OrderedBookDto> {

    List<OrderedBookDto> findByBookId(Long bookId);

    List<OrderedBookDto> findByPurchaseId(Long userId);

}
