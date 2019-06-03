package com.zzpj.controllers;

import com.zzpj.dtos.PaymentStatusDto;
import com.zzpj.entities.PaymentStatus;
import com.zzpj.services.interfaces.PaymentStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/paymentStatus")
public class PaymentStatusController {

    private final PaymentStatusService paymentStatusService;
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentStatusController(PaymentStatusService paymentStatusService, ModelMapper modelMapper) {
        this.paymentStatusService = paymentStatusService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<PaymentStatusDto>> getAllPaymentStatus() {
        List<PaymentStatusDto> paymentStatusDtos = paymentStatusService.findAll()
                .stream()
                .map(paymentStatus ->
                        modelMapper.map(paymentStatus, PaymentStatusDto.class)
                )
                .collect(
                        Collectors.toList());
        return ResponseEntity.ok(paymentStatusDtos);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<PaymentStatusDto> getPaymentStatusById(@PathVariable Long id) {
        PaymentStatus paymentStatus = paymentStatusService.findById(id);
        PaymentStatusDto paymentStatusDto = modelMapper.map(paymentStatus, PaymentStatusDto.class);
        return ResponseEntity.ok(paymentStatusDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addPaymentStatus(@Valid @RequestBody PaymentStatusDto paymentStatusDto) {
        PaymentStatus paymentStatus = modelMapper.map(paymentStatusDto, PaymentStatus.class);
        paymentStatusService.add(paymentStatus);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateCategory(@PathVariable Long id, @RequestBody PaymentStatusDto paymentStatusDto) {
        PaymentStatus paymentStatus = modelMapper.map(paymentStatusDto, PaymentStatus.class);
        paymentStatus.setId(id);
        paymentStatusService.update(paymentStatus);
        return ResponseEntity.ok().build();
    }
}
