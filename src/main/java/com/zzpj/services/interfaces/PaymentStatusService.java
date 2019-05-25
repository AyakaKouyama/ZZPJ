package com.zzpj.services.interfaces;


import com.zzpj.entities.PaymentStatus;
import com.zzpj.repositories.PaymentStatusRepository;
import org.springframework.stereotype.Service;

@Service
public interface PaymentStatusService extends BaseService<PaymentStatusRepository, PaymentStatus> {

    PaymentStatus add(PaymentStatus category);

    PaymentStatus findByName(String name);
}
