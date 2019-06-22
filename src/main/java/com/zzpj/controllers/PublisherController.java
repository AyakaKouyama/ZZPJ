package com.zzpj.controllers;

import com.zzpj.dtos.PublisherDto;
import com.zzpj.entities.Publisher;
import com.zzpj.services.interfaces.PublisherService;
import com.zzpj.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/publishers")
public class PublisherController extends BaseController<Publisher, PublisherDto> {

    private PublisherService service;

    public PublisherController(PublisherService baseService) {
        super(baseService);
        this.service = baseService;
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody PublisherDto dto) {
        PublisherDto created = service.add(dto);
        return new ResponseEntity<PublisherDto>(created, HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id) {
        return super.delete(id);
    }


    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    ResponseEntity<PublisherDto> findByName(@PathVariable String name) {
        return ResponseEntity.ok(service.findByName(name));
    }
}
