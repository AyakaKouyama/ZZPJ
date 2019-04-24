package com.zzpj.services;

import com.zzpj.entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> findAll();

    void addBook(Book book);
}
