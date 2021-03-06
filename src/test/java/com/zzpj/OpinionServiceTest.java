package com.zzpj;

import com.zzpj.entities.Book;
import com.zzpj.repositories.BookRepository;
import com.zzpj.repositories.OpinionRepository;
import com.zzpj.repositories.UserRepository;
import com.zzpj.services.impl.OpinionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpinionServiceTest {

    @Mock
    OpinionRepository opinionRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    BookRepository bookRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    OpinionServiceImpl opinionService = new OpinionServiceImpl(opinionRepository,
            userRepository,
            bookRepository,
            modelMapper);

    @Test
    public void shouldGetAverageRateForBook() {
        when(bookRepository.findAll()).thenReturn(createBookList());
        when(bookRepository.findById(1L)).thenReturn(Optional.of(createBookList().get(0)));
        when(opinionRepository.getAverageRateForBook(1L)).thenReturn(4);

        List<Book> bookList = bookRepository.findAll();
        int rate = opinionService.getAverageRateForBook(1L);
        assertThat(rate).isEqualTo(4);
    }

    private List<Book> createBookList() {
        List<Book> bookList = new ArrayList<>();
        Book book = new Book();
        book.setId(1L);
        bookList.add(book);

        return bookList;
    }
}
