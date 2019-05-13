package com.zzpj.services.impl;

import com.zzpj.entities.Book;
import com.zzpj.repositories.BookRepository;
import com.zzpj.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends BaseServiceImpl<BookRepository, Book> implements BookService {

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        super(bookRepository);
    }
}
