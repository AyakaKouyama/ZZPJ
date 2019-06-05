package com.zzpj.controllers;

import com.zzpj.services.interfaces.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


public class BaseController <TModel, UDto> {
    protected BaseService<TModel, UDto> service;

    @Autowired
    public BaseController(BaseService<TModel, UDto> service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<UDto>> getAll() {
        List<UDto> dtoList = service.findAll();
        return ResponseEntity.ok(dtoList);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody UDto dto) {
        UDto created = service.add(dto);
        return new ResponseEntity<UDto>(created, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<UDto> getById(@PathVariable Long id) {
        UDto result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity update(@PathVariable Long id, @RequestBody UDto dto) {
        UDto result = service.update(id, dto);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

}