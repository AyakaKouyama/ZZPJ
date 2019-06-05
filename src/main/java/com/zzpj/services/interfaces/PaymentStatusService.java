package com.zzpj.services.interfaces;


import com.zzpj.dtos.PaymentStatusDto;
import com.zzpj.entities.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public interface PaymentStatusService extends BaseService<PaymentStatus, PaymentStatusDto> {

}
