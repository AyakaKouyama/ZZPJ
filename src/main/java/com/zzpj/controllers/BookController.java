package com.zzpj.controllers;

import com.zzpj.dtos.BookDto;
import com.zzpj.entities.Book;
import com.zzpj.entities.Category;
import com.zzpj.services.interfaces.BaseService;
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
public class BookController extends BaseController<Book, BookDto>{

    @Autowired
    public BookController(BookService service) {
        super(service);
    }
}
