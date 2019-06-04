package com.zzpj.controllers;

import com.zzpj.dtos.GetOpinionDto;
import com.zzpj.dtos.OpinionDto;
import com.zzpj.entities.Book;
import com.zzpj.entities.Opinion;
import com.zzpj.entities.User;
import com.zzpj.services.interfaces.BookService;
import com.zzpj.services.interfaces.OpinionService;
import com.zzpj.services.interfaces.UserService;
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
@RequestMapping("/opinions")
public class OpinionController {

    private OpinionService opinionService;
    private BookService bookService;
    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public OpinionController(OpinionService opinionService,
            BookService bookService,
            UserService userService,
            ModelMapper modelMapper) {
        this.opinionService = opinionService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<GetOpinionDto>> getAllOpinions() {
        List<GetOpinionDto> opinionDtoList = opinionService.findAll()
                .stream()
                .map(opinion ->
                        modelMapper.map(opinion, GetOpinionDto.class)
                )
                .collect(
                        Collectors.toList());
        return ResponseEntity.ok(opinionDtoList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<GetOpinionDto> getOpinionById(@PathVariable Long id) {
        Opinion opinion = opinionService.findById(id);
        GetOpinionDto result = modelMapper.map(opinion, GetOpinionDto.class);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addOpinion(@Valid @RequestBody OpinionDto opinionDto) {
        Opinion opinion = new Opinion();
        Book book = bookService.findById(opinionDto.getBookId());
        User user = userService.findById(opinionDto.getUserId());
        opinion.setBook(book);
        opinion.setUser(user);
        opinion.setDescription(opinionDto.getDescription());
        opinion.setRate(opinionDto.getRate());
        opinionService.add(opinion);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateBook(@PathVariable Long id, @RequestBody OpinionDto opinionDto) {
        Opinion opinion = modelMapper.map(opinionDto, Opinion.class);
        Book book = bookService.findById(opinionDto.getBookId());
        User user = userService.findById(opinionDto.getUserId());
        opinion.setBook(book);
        opinion.setUser(user);
        opinionService.update(opinion);
        return ResponseEntity.ok(opinion);
    }
}
