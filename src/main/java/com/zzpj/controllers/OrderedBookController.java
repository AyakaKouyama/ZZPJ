package com.zzpj.controllers;

import com.zzpj.dtos.OrderedBookDto;
import com.zzpj.entities.OrderedBook;
import com.zzpj.services.interfaces.OrderedBookService;
import com.zzpj.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orderedBooks")
public class OrderedBookController extends BaseController<OrderedBook, OrderedBookDto> {

    private OrderedBookService service;

    @Override
    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<OrderedBookDto>> findAll() {
        return super.findAll();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody OrderedBookDto dto) {
        OrderedBookDto created = service.add(dto);
        return new ResponseEntity<OrderedBookDto>(created, HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<OrderedBookDto> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id) {
        return super.delete(id);
    }


    @Autowired
    public OrderedBookController(OrderedBookService baseService) {
        super(baseService);
        this.service = baseService;
    }

    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(value = "/{bookId}/book", method = RequestMethod.GET)
    ResponseEntity<List<OrderedBookDto>> findByBookId(@PathVariable Long bookId) {
        return ResponseEntity.ok(service.findByBookId(bookId));
    }

    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(value = "/{purchaseId}/purchase", method = RequestMethod.GET)
    ResponseEntity<List<OrderedBookDto>> findByPurchaseId(@PathVariable Long purchaseId) {
        return ResponseEntity.ok(service.findByPurchaseId(purchaseId));
    }
}
