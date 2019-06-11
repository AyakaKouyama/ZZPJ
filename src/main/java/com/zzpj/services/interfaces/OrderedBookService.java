package com.zzpj.services.interfaces;

import com.zzpj.dtos.OrderedBookDto;
import com.zzpj.entities.OrderedBook;
import org.springframework.stereotype.Service;


@Service
public interface OrderedBookService extends BaseService<OrderedBook, OrderedBookDto> {

}
