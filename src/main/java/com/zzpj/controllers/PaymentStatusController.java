package com.zzpj.controllers;

import com.zzpj.dtos.PaymentStatusDto;
import com.zzpj.entities.PaymentStatus;
import com.zzpj.services.interfaces.PaymentStatusService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paymentStatuses")
public class PaymentStatusController extends BaseController<PaymentStatus, PaymentStatusDto> {

    public PaymentStatusController(PaymentStatusService paymentStatusService) {
        super(paymentStatusService);
    }
}
