package com.zzpj.services.impl;


import com.zzpj.dtos.PaymentStatusDto;
import com.zzpj.entities.PaymentStatus;
import com.zzpj.repositories.PaymentStatusRepository;
import com.zzpj.services.interfaces.PaymentStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentStatusServiceImpl extends BaseServiceImpl<PaymentStatusRepository, PaymentStatus, PaymentStatusDto> implements PaymentStatusService {

    @Autowired
    public PaymentStatusServiceImpl(PaymentStatusRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public PaymentStatusDto ConvertToDto(PaymentStatus entity) {
        PaymentStatusDto paymentStatusDto = modelMapper.map(entity, PaymentStatusDto.class);
        return paymentStatusDto;
    }

    @Override
    public PaymentStatus ConvertToEntity(PaymentStatusDto dto) {
        PaymentStatus paymentStatus = modelMapper.map(dto, PaymentStatus.class);
        return paymentStatus;
    }
}
