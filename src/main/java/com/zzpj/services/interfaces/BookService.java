package com.zzpj.services.interfaces;

import com.zzpj.entities.Book;
import com.zzpj.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public interface BookService extends BaseService<BookRepository, Book> {
    // place to add additional methods for books
}
