package com.zzpj.services.interfaces;

import com.zzpj.dtos.BookDto;
import com.zzpj.entities.Book;
import com.zzpj.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService extends BaseService<Book, BookDto>  {

    List<BookDto> sortField(String field);
}
