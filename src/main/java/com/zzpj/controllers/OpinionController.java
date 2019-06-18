package com.zzpj.controllers;

import com.zzpj.dtos.OpinionDto;
import com.zzpj.entities.Opinion;
import com.zzpj.services.interfaces.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opinions")
public class OpinionController extends BaseController<Opinion, OpinionDto>{

    //this service is extended by methods for opinions
    private OpinionService service;

    @Autowired
    public OpinionController(OpinionService baseService) {
        super(baseService);
        this.service = baseService;
    }

    @RequestMapping(value = "/{userId}/user" ,method = RequestMethod.GET)
    ResponseEntity<List<OpinionDto>> findByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(service.findByUserId(userId));
    }

    @RequestMapping(value = "/{bookId}/book" ,method = RequestMethod.GET)
    ResponseEntity<List<OpinionDto>> findByBookId(@PathVariable Long bookId){
        return ResponseEntity.ok(service.findByBookId(bookId));
    }

    @RequestMapping(value = "/{bookId}/book/avg" ,method = RequestMethod.GET)
    ResponseEntity<Integer> getAverageRateForBook(@PathVariable  Long bookId){
        return ResponseEntity.ok(service.getAverageRateForBook(bookId));
    }
}
