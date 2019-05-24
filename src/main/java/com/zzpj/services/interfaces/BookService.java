package com.zzpj.services.interfaces;

import com.zzpj.entities.Book;
import com.zzpj.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public interface BookService extends BaseService<BookRepository, Book> {

    Book add(Book book);

    Book update(Book book);
}