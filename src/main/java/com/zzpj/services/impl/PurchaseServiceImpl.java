package com.zzpj.services.impl;


import com.zzpj.dtos.PurchaseDto;
import com.zzpj.entities.PaymentStatus;
import com.zzpj.entities.Purchase;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.repositories.PaymentStatusRepository;
import com.zzpj.repositories.PurchaseRepository;
import com.zzpj.repositories.ShippingMethodRepository;
import com.zzpj.services.interfaces.PurchaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl extends BaseServiceImpl<PurchaseRepository, Purchase, PurchaseDto> implements PurchaseService {

    private ShippingMethodRepository shippingMethodRepository;
    private PaymentStatusRepository paymentStatusRepository;

    @Autowired
    public PurchaseServiceImpl(
            PurchaseRepository repository,
            ShippingMethodRepository shippingMethodRepository,
            PaymentStatusRepository paymentStatusRepository,
            ModelMapper modelMapper)
    {
        super(repository, modelMapper);
        this.shippingMethodRepository = shippingMethodRepository;
        this.paymentStatusRepository = paymentStatusRepository;
    }

    @Override
    public PurchaseDto convertToDto(Purchase entity) {
        PurchaseDto bookDto = modelMapper.map(entity, PurchaseDto.class);
        return bookDto;
    }

    @Override
    public Purchase convertToEntity(PurchaseDto dto) {
        Purchase purchase = modelMapper.map(dto, Purchase.class);

        ShippingMethod shippingMethod = shippingMethodRepository.findById(dto.getShippingMethod().getId())
                .orElseThrow(() -> super.entityNotFoundException(dto.getShippingMethod().getId(), "ShippingMethod"));
        PaymentStatus paymentStatus = paymentStatusRepository.findById(dto.getPaymentStatus().getId())
                .orElseThrow(() -> super.entityNotFoundException(dto.getPaymentStatus().getId(), "PaymentStatus"));

        purchase.setShippingMethod(shippingMethod);
        purchase.setPaymentStatus(paymentStatus);

        return purchase;
    }
}

