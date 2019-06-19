package com.zzpj;

import com.zzpj.entities.Book;
import com.zzpj.repositories.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Book book = new Book();
        book.setId(1L);
        book.setAuthor("test");
        book.setPrice(new BigDecimal(10.0));
        book.setTitle("title");

        entityManager.persist(book);  // te dwie linijki muszą być. spring w apce robi to automatycznie. w testach trzebac zrobić to ręcznie.
        entityManager.flush();        //

        // when
        Optional<Book> foundBook = bookRepository.findById(1L);

        // then
        assertThat(foundBook.isPresent()).isEqualTo(true);
        assertThat(foundBook.get().getAuthor()).isEqualTo("test");
        assertThat(foundBook.get().getId()).isEqualTo(1L);
        assertThat(foundBook.get().getTitle()).isEqualTo("title");
    }
}
