package com.zzpj.controllers;

import com.zzpj.dtos.PurchaseDto;
import com.zzpj.entities.Purchase;
import com.zzpj.services.impl.PurchaseServiceImpl;
import com.zzpj.services.interfaces.BaseService;
import com.zzpj.services.interfaces.BookService;
import com.zzpj.services.interfaces.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class PurchaseController extends BaseController<Purchase, PurchaseDto> {

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService){
        super(purchaseService);
        this.purchaseService = purchaseService;
    }





}
