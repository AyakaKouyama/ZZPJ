package com.zzpj.controllers;

import com.zzpj.dtos.OpinionDto;
import com.zzpj.entities.Opinion;
import com.zzpj.services.interfaces.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/opinions")
public class OpinionController extends BaseController<Opinion, OpinionDto>{

    @Autowired
    public OpinionController(OpinionService service) {
        super(service);
    }
}
