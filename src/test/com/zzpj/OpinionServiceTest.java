import com.zzpj.dtos.BookDto;
import com.zzpj.dtos.OpinionDto;
import com.zzpj.entities.Book;
import com.zzpj.entities.Opinion;
import com.zzpj.repositories.*;
import com.zzpj.services.impl.BookServiceImpl;
import com.zzpj.services.impl.OpinionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import javax.swing.text.html.Option;
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

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    PublisherRepository publisherRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    BookServiceImpl bookService = new BookServiceImpl(bookRepository, categoryRepository, publisherRepository, modelMapper);

    @InjectMocks
    OpinionServiceImpl opinionService = new OpinionServiceImpl(opinionRepository, userRepository, bookRepository, modelMapper);

    @Test
    public void shouldGetAverageRateForBook(){
        /*when(bookRepository.findAll()).thenReturn(createBookList());
        when(opinionRepository.findAll()).thenReturn(creteOpinionList());

        List<Book> bookList = bookRepository.findAll();

        int rate = opinionService.getAverageRateForBook(1L);

        assertThat(rate).isEqualTo(4);*/
    }

    private List<Opinion> creteOpinionList(){

        List<Opinion> opinionList = new ArrayList<>();

        Opinion opinion1 = new Opinion();
        opinion1.setRate(3);
        opinionList.add(opinion1);

        Opinion opinion2 = new Opinion();
        opinion2.setRate(5);
        opinionList.add(opinion2);

        return opinionList;
    }

    private List<Book> createBookList(){
        List<Book> bookList = new ArrayList<>();

        Book book = new Book();
        book.setId(1L);

        bookList.add(book);

        return bookList;
    }
}
