package com.zzpj.controllers;

import com.zzpj.dtos.PublisherDto;
import com.zzpj.entities.Publisher;
import com.zzpj.services.interfaces.BaseService;
import com.zzpj.services.interfaces.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
public class PublisherController extends BaseController<Publisher, PublisherDto> {

    private PublisherService service;

    @Autowired
    public PublisherController(PublisherService baseService) {
        super(baseService);
        this.service = baseService;
    }

    @RequestMapping(value = "/name/{name}" ,method = RequestMethod.GET)
    ResponseEntity<PublisherDto> findByBookId(@PathVariable String name){
        return ResponseEntity.ok(service.findByName(name));
    }


}
