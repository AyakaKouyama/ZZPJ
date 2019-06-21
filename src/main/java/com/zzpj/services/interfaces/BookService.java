package com.zzpj.services.interfaces;

import com.zzpj.dtos.BookDto;
import com.zzpj.entities.Book;
import com.zzpj.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface BookService extends BaseService<Book, BookDto>  {

    List<BookDto> sortField(String field);
    List<BookDto> filterField(String field, String param);
    List<BookDto> priceFilter(String lowestPrice, String highestPrice);

}
