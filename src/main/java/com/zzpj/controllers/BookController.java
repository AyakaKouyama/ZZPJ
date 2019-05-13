package com.zzpj.controllers;

import com.zzpj.dtos.BookDto;
import com.zzpj.entities.Book;
import com.zzpj.services.interfaces.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    private ModelMapper modelMapper;

    @Autowired
    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping
    ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        Book insertedBook = bookService.add(book);
        if(insertedBook != null){
            BookDto result = modelMapper.map(insertedBook, BookDto.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    @RequestMapping("/{id}")
    ResponseEntity<BookDto> save(@PathVariable  Long id, @RequestBody BookDto bookDto){
        Book book = modelMapper.map(bookDto, Book.class);
        Book updatedBook = bookService.save(id, book);
        if(updatedBook != null){
            BookDto result = modelMapper.map(updatedBook, BookDto.class);
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    @RequestMapping("/{id}")
    ResponseEntity<BookDto> getById(@PathVariable  Long id){
        Optional<Book> book = bookService.findById(id);
        if(!book.isEmpty()){
            BookDto result = modelMapper.map(book.get(), BookDto.class);
           return ResponseEntity.ok(result);
       }

        return ResponseEntity.badRequest().build();
    }
}
