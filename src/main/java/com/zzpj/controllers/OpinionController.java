package com.zzpj.controllers;

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
import org.springframework.web.bind.annotation.*;

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
                             ModelMapper modelMapper){
        this.opinionService = opinionService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<OpinionDto>> getAllOpinions() {
        List<OpinionDto> opinionDtoList = opinionService.findAll()
                .stream()
                .map(opinion ->
                        modelMapper.map(opinion, OpinionDto.class)
                )
                .collect(
                        Collectors.toList());
        return ResponseEntity.ok(opinionDtoList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<OpinionDto> getOpinionById(@PathVariable Long id) {
        Opinion opinion = opinionService.findById(id);
        OpinionDto result = modelMapper.map(opinion, OpinionDto.class);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addOpinion(@Valid @RequestBody OpinionDto opinionDto) {
        Opinion opinion = modelMapper.map(opinionDto, Opinion.class);
        Book book = bookService.findById(opinionDto.getBook().getId());
        User user = userService.findById(opinionDto.getUser().getId());
        opinion.setBook(book);
        opinion.setUser(user);
        opinionService.add(opinion);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateBook(@PathVariable Long id, @RequestBody OpinionDto opinionDto) {
        Opinion opinion = modelMapper.map(opinionDto, Opinion.class);
        Book book = bookService.findById(opinionDto.getBook().getId());
        User user = userService.findById(opinionDto.getUser().getId());
        opinion.setBook(book);
        opinion.setUser(user);
        opinionService.update(opinion);
        return ResponseEntity.ok(opinion);
    }

}
