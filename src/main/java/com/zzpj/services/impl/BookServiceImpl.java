package com.zzpj.services.impl;

import com.zzpj.dtos.BookDto;
import com.zzpj.dtos.CategoryDto;
import com.zzpj.entities.Book;
import com.zzpj.entities.Category;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.BookRepository;
import com.zzpj.repositories.CategoryRepository;
import com.zzpj.services.interfaces.BookService;
import com.zzpj.services.interfaces.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl extends BaseServiceImpl<BookRepository, Book, BookDto> implements BookService {

    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        super(bookRepository, modelMapper);
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto convertToDto(Book entity) {
        BookDto bookDto = modelMapper.map(entity, BookDto.class);
        return bookDto;
    }

    @Override
    public Book convertToEntity(BookDto dto) {
        Book book = modelMapper.map(dto, Book.class);
        Category category = categoryRepository.findById(dto.getCategory().getId())
                .orElseThrow(() -> super.entityNotFoundException(dto.getCategory().getId(), "Book"));
        book.setCategory(category);
        return book;
    }

    @Override
    public List<BookDto> sortField(String filed){
        List<Book> books = bookRepository.findAll();

        switch(filed){
            case("title"): {
                List<Book> sorted = books.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
                return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
            }
            case("author"): {
                List<Book> sorted = books.stream().sorted(Comparator.comparing(Book::getAuthor)).collect(Collectors.toList());
                return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
            }
            default: {
                return books.stream().map(this::convertToDto).collect(Collectors.toList());
            }
        }
    }

}
