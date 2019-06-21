package com.zzpj.controllers;

import com.zzpj.dtos.PublisherDto;
import com.zzpj.entities.Publisher;
import com.zzpj.services.interfaces.BaseService;
import com.zzpj.services.interfaces.PublisherService;
import com.zzpj.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController extends BaseController<Publisher, PublisherDto> {

    private PublisherService service;

    @Autowired
    public PublisherController(PublisherService baseService) {
        super(baseService);
        this.service = baseService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<PublisherDto>> findAll() {
        return super.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody PublisherDto dto) {
        PublisherDto created = service.add(dto);
        return new ResponseEntity<PublisherDto>(created, HttpStatus.CREATED);
    }


    @Override
    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<PublisherDto> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity update(@PathVariable Long id, @RequestBody PublisherDto dto) {
        return super.update(id, dto);
    }


    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id){
        return super.delete(id);
    }

    @RequestMapping(value = "/name/{name}" ,method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    ResponseEntity<PublisherDto> findByName(@PathVariable String name){
        return ResponseEntity.ok(service.findByName(name));
    }


}
