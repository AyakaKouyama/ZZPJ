package com.zzpj.controllers;

import com.zzpj.dtos.OrderedBookDto;
import com.zzpj.entities.OrderedBook;
import com.zzpj.services.interfaces.OrderedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderedBooks")
public class OrderedBookController extends BaseController<OrderedBook, OrderedBookDto> {

    private OrderedBookService service;

    @Autowired
    public OrderedBookController(OrderedBookService baseService) {
        super(baseService);
        this.service = baseService;
    }

    @RequestMapping(value = "/{bookId}/book" ,method = RequestMethod.GET)
    ResponseEntity<List<OrderedBookDto>> findByBookId(@PathVariable Long bookId){
        return ResponseEntity.ok(service.findByBookId(bookId));
    }

    @RequestMapping(value = "/{purchaseId}/purchase" ,method = RequestMethod.GET)
    ResponseEntity<List<OrderedBookDto>> findByPurchaseId(@PathVariable Long purchaseId){
        return ResponseEntity.ok(service.findByPurchaseId(purchaseId));
    }
}
