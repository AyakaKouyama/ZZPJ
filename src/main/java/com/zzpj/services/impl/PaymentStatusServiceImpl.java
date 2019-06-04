package com.zzpj.services.impl;


import com.zzpj.entities.PaymentStatus;
import com.zzpj.exceptions.EntityAlreadyExistsException;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.PaymentStatusRepository;
import com.zzpj.services.interfaces.PaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentStatusServiceImpl extends BaseServiceImpl<PaymentStatusRepository, PaymentStatus> implements
        PaymentStatusService {

    private final PaymentStatusRepository paymentStatusRepository;

    @Autowired
    public PaymentStatusServiceImpl(PaymentStatusRepository paymentStatusRepository) {
        super(paymentStatusRepository);
        this.paymentStatusRepository = paymentStatusRepository;
    }

    @Override
    public PaymentStatus add(PaymentStatus paymentStatus) {
        if (paymentStatusRepository.existsByName(paymentStatus.getName())) {
            throw entityAlreadyExistsException(paymentStatus.getName());
        }

        paymentStatus.setVersion(0L);
        return paymentStatusRepository.save(paymentStatus);
    }

    @Override
    public PaymentStatus findByName(String name) {
        return paymentStatusRepository.findByName(name).orElseThrow(() -> entityNotFoundException(name));
    }

    private EntityNotFoundException entityNotFoundException(String name) {
        return new EntityNotFoundException("Payment status with name " + name + " not found.");
    }

    private EntityAlreadyExistsException entityAlreadyExistsException(String name) {
        return new EntityAlreadyExistsException("Payment status with name " + name + " already exists.");
    }
}
