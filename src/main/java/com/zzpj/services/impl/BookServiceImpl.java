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

import java.math.BigDecimal;
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
            case("price"): {
                List<Book> sorted = books.stream().sorted(Comparator.comparing(Book::getPrice)).collect(Collectors.toList());
                return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
            }
            case("numberOfPages"): {
                List<Book> sorted = books.stream().sorted(Comparator.comparing(Book::getNumberOfPages)).collect(Collectors.toList());
                return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
            }
            default: {
                return books.stream().map(this::convertToDto).collect(Collectors.toList());
            }
        }
    }

    @Override
    public List<BookDto> filterField(String field, String param){
        List<BookDto> dto = null;

        switch (field)
        {
            case "title":
                dto =  this.titleFilter(param);
                break;
            case "phraseInTitle":
                dto =  this.phraseInTitleFilter(param);
                break;
            case "author":
                dto =  this.authorFilter(param);
                break;
            case "publisher":
                dto =  this.publisherFilter(param);
                break;
        }

        return dto;
    }


    public List<BookDto> titleFilter(String title) {
        List<Book> books = bookRepository.findAll();

        List<Book> sorted = books.stream().filter(b -> b.getTitle().equals(title)).collect(Collectors.toList());
        return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    public List<BookDto> phraseInTitleFilter(String phrase) {
        List<Book> books = bookRepository.findAll();

        List<Book> sorted = books.stream().filter(b -> b.getAuthor().contains(phrase)).collect(Collectors.toList());
        return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    public List<BookDto> authorFilter(String author) {
        List<Book> books = bookRepository.findAll();

        List<Book> sorted = books.stream().filter(b -> b.getAuthor().equals(author)).collect(Collectors.toList());
        return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<BookDto> publisherFilter(String publisherName) {
        //TODO no reference to publisher xd?
        return null;
    }


    public List<BookDto> priceFilter(String lowestPrice, String highestPrice) {
        List<Book> books = bookRepository.findAll();

        List<Book> sorted = books.stream()
                .filter(b -> b.getPrice().compareTo(new BigDecimal(lowestPrice)) < 0)
                .filter(b -> b.getPrice().compareTo(new BigDecimal(highestPrice)) > 0)
                .collect(Collectors.toList());
        return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
