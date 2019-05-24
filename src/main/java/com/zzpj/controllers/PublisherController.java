package com.zzpj.controllers;

import com.zzpj.dtos.PublisherDto;
import com.zzpj.entities.Publisher;
import com.zzpj.services.interfaces.PublisherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherService publisherService;
    private final ModelMapper modelMapper;

    @Autowired
    public PublisherController(PublisherService publisherService, ModelMapper modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<PublisherDto>> getAllPublishers() {
        List<PublisherDto> publisherDtos = publisherService.findAll()
                .stream()
                .map(publisher ->
                        modelMapper.map(publisher, PublisherDto.class)
                )
                .collect(
                        Collectors.toList());
        return ResponseEntity.ok(publisherDtos);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<PublisherDto> getPublisherById(@PathVariable Long id) {
        Publisher publisher = publisherService.findById(id);
        PublisherDto publisherDto = modelMapper.map(publisher, PublisherDto.class);
        return ResponseEntity.ok(publisherDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addCategory(@Valid @RequestBody PublisherDto publisherDto) {
        Publisher publisher = modelMapper.map(publisherDto, Publisher.class);
        publisherService.add(publisher);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateCategory(@PathVariable Long id, @RequestBody PublisherDto categoryDto) {
        Publisher publisher = modelMapper.map(categoryDto, Publisher.class);
        publisher.setId(id);
        publisherService.update(publisher);
        return ResponseEntity.ok().build();
    }
}