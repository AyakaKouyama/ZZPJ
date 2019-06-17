package com.zzpj.services.interfaces;

import com.zzpj.dtos.OrderedBookDto;
import com.zzpj.entities.OrderedBook;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface OrderedBookService extends BaseService<OrderedBook, OrderedBookDto> {

    List<OrderedBookDto> findByBookId(Long bookId);

    List<OrderedBookDto> findByPurchaseId(Long userId);

}
