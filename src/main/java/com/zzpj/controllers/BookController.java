package com.zzpj.controllers;

import com.zzpj.dtos.BookDto;
import com.zzpj.entities.Book;
import com.zzpj.services.interfaces.BookService;
import com.zzpj.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController extends BaseController<Book, BookDto> {

    private BookService bookService;

    public BookController(BookService service) {
        super(service);
        this.bookService = service;
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody BookDto dto) {
        BookDto created = service.add(dto);
        return new ResponseEntity<BookDto>(created, HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity update(@PathVariable Long id, @RequestBody BookDto dto) {
        return super.update(id, dto);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    ResponseEntity<List<BookDto>> sortByParam(@RequestParam(value = "sort", required = false) String filtredFiled) {
        List<BookDto> dtos = bookService.sortField(filtredFiled);
        return ResponseEntity.ok(dtos);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    ResponseEntity<List<BookDto>> filterByParam(@RequestParam(value = "filter", required = false) String filterType,
            @RequestParam(value = "param", required = false) String param) {
        List<BookDto> dtos = bookService.filterField(filterType, param);

        return ResponseEntity.ok(dtos);
    }

    @RequestMapping(value = "/filterPrice", method = RequestMethod.GET)
    ResponseEntity<List<BookDto>> filterByPrice(
            @RequestParam(value = "lowestPrice", required = false) String lowestPrice,
            @RequestParam(value = "highestPrice", required = false) String highestPrice) {

        List<BookDto> dtos = bookService.priceFilter(lowestPrice, highestPrice);
        return ResponseEntity.ok(dtos);
    }
}
