package com.zzpj.services.interfaces;

import com.zzpj.dtos.BookDto;
import com.zzpj.entities.Book;

import java.util.List;

public interface BookService extends BaseService<Book, BookDto>  {

    BookDto update(BookDto bookDto);

    List<BookDto> sortField(String field);

    List<BookDto> filterField(String field, String param);

    List<BookDto> priceFilter(String lowestPrice, String highestPrice);
}
