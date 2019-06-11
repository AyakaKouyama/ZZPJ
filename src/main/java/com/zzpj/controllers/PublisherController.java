package com.zzpj.controllers;

import com.zzpj.dtos.PublisherDto;
import com.zzpj.entities.Publisher;
import com.zzpj.services.interfaces.BaseService;
import com.zzpj.services.interfaces.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
public class PublisherController extends BaseController<Publisher, PublisherDto> {

    @Autowired
    public PublisherController(PublisherService service) {
        super(service);
    }
}
