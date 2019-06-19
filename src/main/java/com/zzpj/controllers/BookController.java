package com.zzpj.controllers;

import com.zzpj.dtos.BookDto;
import com.zzpj.entities.Book;
import com.zzpj.entities.Category;
import com.zzpj.services.interfaces.BaseService;
import com.zzpj.services.interfaces.BookService;
import com.zzpj.services.interfaces.CategoryService;
import com.zzpj.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController extends BaseController<Book, BookDto>{

    public BookController(BookService service) {
        super(service);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    ResponseEntity add(@Valid @RequestBody BookDto dto) {
       return super.add(dto);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<BookDto> getById(@PathVariable Long id) {
        return super.getById(id);
    }
}
