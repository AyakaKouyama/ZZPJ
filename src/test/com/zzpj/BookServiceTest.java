package com.zzpj;

import com.zzpj.dtos.BookDto;
import com.zzpj.dtos.CategoryDto;
import com.zzpj.dtos.PublisherDto;
import com.zzpj.entities.Book;
import com.zzpj.entities.Category;
import com.zzpj.entities.Publisher;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.BookRepository;
import com.zzpj.repositories.CategoryRepository;
import com.zzpj.repositories.PublisherRepository;
import com.zzpj.services.impl.BookServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    PublisherRepository publisherRepository;


    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    BookServiceImpl bookService = new BookServiceImpl(bookRepository, categoryRepository, publisherRepository, modelMapper);

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenCategoryDoesNotExists() {
        Book book = createBook();
        book.setVersion(0L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookService.add(createBookDto()));
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenPublisherDoesNotExists() {
        Book book = createBook();
        book.setVersion(0L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(createCategory()));
        when(publisherRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookService.add(createBookDto()));
    }

    @Test
    public void shouldSortByTitle(){
        when(bookRepository.findAll()).thenReturn(createBookList());

        List<BookDto> sorted = bookService.sortField("title");
        assertThat(sorted.get(0).getTitle()).isSameAs("a");
        assertThat(sorted.get(1).getTitle()).isSameAs("c");
        assertThat(sorted.get(2).getTitle()).isSameAs("g");
        assertThat(sorted.get(3).getTitle()).isSameAs("h");
        assertThat(sorted.get(4).getTitle()).isSameAs("k");
        assertThat(sorted.get(5).getTitle()).isSameAs("o");
    }

    @Test
    public void shouldSortByAuthor(){
        when(bookRepository.findAll()).thenReturn(createBookList());

        List<BookDto> sorted = bookService.sortField("author");
        assertThat(sorted.get(0).getAuthor()).isSameAs("a");
        assertThat(sorted.get(1).getAuthor()).isSameAs("c");
        assertThat(sorted.get(2).getAuthor()).isSameAs("g");
        assertThat(sorted.get(3).getAuthor()).isSameAs("h");
        assertThat(sorted.get(4).getAuthor()).isSameAs("k");
        assertThat(sorted.get(5).getAuthor()).isSameAs("o");
    }

    private Book createBook() {
        Book book = new Book();
        book.setTitle("test title");
        book.setAuthor("test author");
        book.setIsbn("random");
        book.setDescription("description");

        Category category = new Category();
        category.setName("test category");
        category.setId(1L);

        book.setCategory(category);
        book.setNumberOfPages(123);
        book.setPrice(BigDecimal.ONE);

        Publisher publisher = new Publisher();
        publisher.setName("test publisher");

        book.setPublisher(publisher);
        book.setId(1L);

        return book;
    }

    private BookDto createBookDto() {
        BookDto book = new BookDto();
        book.setTitle("test title");
        book.setAuthor("test author");
        book.setIsbn("random");
        book.setDescription("description");

        CategoryDto category = new CategoryDto();
        category.setName("test category");
        category.setId(1L);

        book.setCategory(category);
        book.setNumberOfPages(123);
        book.setPrice(BigDecimal.ONE);

        PublisherDto publisher = new PublisherDto();
        publisher.setName("test publisher");
        publisher.setId(1L);

        book.setPublisher(publisher);

        return book;
    }

    private Category createCategory() {
        Category category = new Category();
        category.setName("test category");
        category.setId(1L);

        return category;
    }

    private Publisher createPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("test publisher");
        publisher.setId(1L);

        return publisher;
    }


    private List<Book> createBookList(){
        List<Book> books = new ArrayList<>();

        Book book = new Book();
        book.setTitle("c");
        book.setAuthor("c");
        books.add(book);

        Book book1 = new Book();
        book1.setTitle("k");
        book1.setAuthor("k");
        books.add(book1);

        Book book2 = new Book();
        book2.setTitle("o");
        book2.setAuthor("o");
        books.add(book2);

        Book book3 = new Book();
        book3.setTitle("g");
        book3.setAuthor("g");
        books.add(book3);

        Book book4 = new Book();
        book4.setTitle("a");
        book4.setAuthor("a");
        books.add(book4);

        Book book5 = new Book();
        book5.setTitle("h");
        book5.setAuthor("h");
        books.add(book5);

        return books;
    }

}
