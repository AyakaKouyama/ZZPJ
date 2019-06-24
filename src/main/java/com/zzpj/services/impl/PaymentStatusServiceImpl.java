package com.zzpj.services.impl;

import com.zzpj.dtos.PaymentStatusDto;
import com.zzpj.entities.PaymentStatus;
import com.zzpj.exceptions.EntityAlreadyExistsException;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.PaymentStatusRepository;
import com.zzpj.services.interfaces.PaymentStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentStatusServiceImpl extends BaseServiceImpl<PaymentStatusRepository, PaymentStatus, PaymentStatusDto> implements
        PaymentStatusService {

    @Autowired
    public PaymentStatusServiceImpl(PaymentStatusRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public PaymentStatusDto convertToDto(PaymentStatus entity) {
        PaymentStatusDto paymentStatusDto = modelMapper.map(entity, PaymentStatusDto.class);
        return paymentStatusDto;
    }

    @Override
    public PaymentStatus convertToEntity(PaymentStatusDto dto) {
        PaymentStatus paymentStatus = modelMapper.map(dto, PaymentStatus.class);
        return paymentStatus;
    }

    @Override
    public PaymentStatusDto add(PaymentStatusDto paymentStatusDto) {
        if (repository.existsByName(paymentStatusDto.getName())) {
            throw new EntityAlreadyExistsException("Payment status with name " + paymentStatusDto.getName() + " already exists.");
        }
        PaymentStatus savedEntity = repository.save(convertToEntity(paymentStatusDto));
        savedEntity.setVersion(0L);
        return convertToDto(savedEntity);
    }

    @Override
    public PaymentStatus getPaymentStatusByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Payment status with name " + name + " not found."));
    }
}
