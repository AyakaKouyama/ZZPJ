package com.zzpj.controllers;

import com.zzpj.dtos.BookDto;
import com.zzpj.dtos.GetBookDto;
import com.zzpj.entities.Book;
import com.zzpj.entities.Category;
import com.zzpj.services.interfaces.BookService;
import com.zzpj.services.interfaces.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private CategoryService categoryService;
    private ModelMapper modelMapper;

    @Autowired
    public BookController(BookService bookService, CategoryService categoryService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<GetBookDto>> getAllBooks() {
        List<GetBookDto> bookDtoList = bookService.findAll()
                .stream()
                .map(book ->
                        modelMapper.map(book, GetBookDto.class)
                )
                .collect(
                        Collectors.toList());
        return ResponseEntity.ok(bookDtoList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<GetBookDto> getBookById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        GetBookDto result = modelMapper.map(book, GetBookDto.class);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addBook(@Valid @RequestBody BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        Category category = categoryService.findById(bookDto.getCategoryId());
        book.setCategory(category);
        bookService.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        Category category = categoryService.findById(bookDto.getCategoryId());
        book.setId(id);
        book.setCategory(category);
        bookService.update(book);
        return ResponseEntity.ok(book);
    }
}
