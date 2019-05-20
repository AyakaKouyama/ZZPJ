package com.zzpj.services.impl;

import com.zzpj.entities.Book;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.BookRepository;
import com.zzpj.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends BaseServiceImpl<BookRepository, Book> implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        super(bookRepository);
        this.bookRepository = bookRepository;
    }

    @Override
    public Book add(Book book) {
        book.setVersion(0L);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        Book bookFromRepository = bookRepository.findById(book.getId())
                .orElseThrow(() -> entityNotFoundException(book.getId()));
        book.setVersion(bookFromRepository.getVersion());
        return bookRepository.save(book);
    }

    private EntityNotFoundException entityNotFoundException(Long id) {
        return new EntityNotFoundException("Book with id " + id + " not found");
    }
}
