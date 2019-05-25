package com.zzpj.controllers;

import com.zzpj.dtos.OpinionDto;
import com.zzpj.entities.Opinion;
import com.zzpj.services.interfaces.OpinionService;
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
    private ModelMapper modelMapper;



    @Autowired
    public OpinionController(OpinionService opinionService, ModelMapper modelMapper){
        this.opinionService = opinionService;
        this.modelMapper = modelMapper;
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

//    @RequestMapping(method = RequestMethod.POST)
//    ResponseEntity addOpinion(@Valid @RequestBody OpinionDto bookDto) {
//        Opinion book = modelMapper.map(bookDto, Opinion.class);
//        Category category = categoryService.findById(bookDto.getCategory().getId());
//        book.setCategory(category);
//        bookService.add(book);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

}
