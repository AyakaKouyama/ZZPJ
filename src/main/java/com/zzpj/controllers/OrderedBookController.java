package com.zzpj.controllers;

import com.zzpj.dtos.GetOrderedBookDto;
import com.zzpj.dtos.OrderedBookDto;
import com.zzpj.entities.Book;
import com.zzpj.entities.OrderedBook;
import com.zzpj.entities.Purchase;
import com.zzpj.services.interfaces.BookService;
import com.zzpj.services.interfaces.OrderedBookService;
import com.zzpj.services.interfaces.PurchaseService;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/orderedBooks")
public class OrderedBookController {

    private PurchaseService purchaseService;
    private BookService bookService;
    private OrderedBookService orderedBookService;
    private ModelMapper modelMapper;

    public OrderedBookController(
            PurchaseService purchaseService,
            BookService bookService,
            OrderedBookService orderedBookService,
            ModelMapper modelMapper
    ) {
        this.bookService = bookService;
        this.purchaseService = purchaseService;
        this.orderedBookService = orderedBookService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<GetOrderedBookDto>> getAllBooks() {
        List<GetOrderedBookDto> orderedBookDtoList = orderedBookService.findAll()
                .stream()
                .map(orderedBook ->
                        modelMapper.map(orderedBook, GetOrderedBookDto.class)
                )
                .collect(
                        Collectors.toList());
        return ResponseEntity.ok(orderedBookDtoList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<GetOrderedBookDto> getBookById(@PathVariable Long id) {
        OrderedBook orderedBook = orderedBookService.findById(id);
        GetOrderedBookDto result = modelMapper.map(orderedBook, GetOrderedBookDto.class);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addBook(@Valid @RequestBody OrderedBookDto orderedBookDto) {
        OrderedBook orderedBook = new OrderedBook();
        Book book = bookService.findById(orderedBookDto.getBookId());
        Purchase purchase = purchaseService.findById(orderedBookDto.getPurchaseId());
        orderedBook.setPurchase(purchase);
        orderedBook.setBook(book);
        orderedBookService.add(orderedBook);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateBook(@PathVariable Long id, @RequestBody OrderedBookDto orderedBookDto) {
        OrderedBook orderedBook = modelMapper.map(orderedBookDto, OrderedBook.class);
        Book book = bookService.findById(orderedBookDto.getBookId());
        Purchase purchase = purchaseService.findById(orderedBookDto.getPurchaseId());
        orderedBook.setPurchase(purchase);
        orderedBook.setBook(book);
        orderedBookService.update(orderedBook);
        return ResponseEntity.ok(orderedBook);
    }
}
