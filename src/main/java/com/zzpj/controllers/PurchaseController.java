package com.zzpj.controllers;


import com.zzpj.dtos.GetPurchaseDto;
import com.zzpj.dtos.PurchaseDto;
import com.zzpj.entities.PaymentStatus;
import com.zzpj.entities.Purchase;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.services.interfaces.PaymentStatusService;
import com.zzpj.services.interfaces.PurchaseService;
import com.zzpj.services.interfaces.ShippingMethodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private PurchaseService purchaseService;
    private PaymentStatusService paymentStatusService;
    private ShippingMethodService shippingMethodService;
    private ModelMapper modelMapper;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, PaymentStatusService paymentStatusService,
            ShippingMethodService shippingMethodService, ModelMapper modelMapper) {
        this.purchaseService = purchaseService;
        this.paymentStatusService = paymentStatusService;
        this.shippingMethodService = shippingMethodService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<GetPurchaseDto>> getAllPurchases() {
        List<GetPurchaseDto> purchaseDtoList = purchaseService.findAll()
                .stream()
                .map(purchase ->
                        modelMapper.map(purchase, GetPurchaseDto.class)
                )
                .collect(
                        Collectors.toList());
        return ResponseEntity.ok(purchaseDtoList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<PurchaseDto> getPurchaseById(@PathVariable Long id) {
        Purchase purchase = purchaseService.findById(id);
        PurchaseDto result = modelMapper.map(purchase, PurchaseDto.class);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addPurchase(@Valid @RequestBody PurchaseDto purchaseDto) {

        Purchase purchase = modelMapper.map(purchaseDto, Purchase.class);
        ShippingMethod shippingMethod = shippingMethodService.findById(purchaseDto.getShippingMethodId());
        PaymentStatus paymentStatus = paymentStatusService.findById(purchaseDto.getPaymentStatusId());

        purchase.setShippingMethod(shippingMethod);
        purchase.setPaymentStatus(paymentStatus);
        purchaseService.add(purchase);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updatePurchase(@PathVariable Long id, @RequestBody PurchaseDto purchaseDto) {
        Purchase purchase = modelMapper.map(purchaseDto, Purchase.class);
        ShippingMethod shippingMethod = shippingMethodService.findById(purchaseDto.getShippingMethodId());
        PaymentStatus paymentStatus = paymentStatusService.findById(purchaseDto.getPaymentStatusId());

        purchase.setShippingMethod(shippingMethod);
        purchase.setPaymentStatus(paymentStatus);
        purchase.setId(id);
        purchaseService.update(purchase);

        return ResponseEntity.ok(purchase);
    }
}
