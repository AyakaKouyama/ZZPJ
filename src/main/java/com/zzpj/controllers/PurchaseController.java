package com.zzpj.controllers;

import com.zzpj.dtos.PurchaseDto;
import com.zzpj.entities.Purchase;
import com.zzpj.services.impl.PurchaseServiceImpl;
import com.zzpj.services.interfaces.BaseService;
import com.zzpj.services.interfaces.BookService;
import com.zzpj.services.interfaces.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public class PurchaseController extends BaseController<Purchase, PurchaseDto> {

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService){
        super(purchaseService);
        this.purchaseService = purchaseService;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody PurchaseDto dto) {

        PurchaseDto created = purchaseService.add(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


}
