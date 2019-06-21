package com.zzpj.services.impl;


import com.zzpj.dtos.PurchaseDto;
import com.zzpj.entities.OrderedBook;
import com.zzpj.entities.PaymentStatus;
import com.zzpj.entities.Purchase;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.repositories.OrderedBookRepository;
import com.zzpj.repositories.PaymentStatusRepository;
import com.zzpj.repositories.PurchaseRepository;
import com.zzpj.repositories.ShippingMethodRepository;
import com.zzpj.services.interfaces.PurchaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl extends BaseServiceImpl<PurchaseRepository, Purchase, PurchaseDto> implements PurchaseService {

    private ShippingMethodRepository shippingMethodRepository;
    private PaymentStatusRepository paymentStatusRepository;
    private OrderedBookRepository orderedBookRepository;

    @Autowired
    public PurchaseServiceImpl(
            PurchaseRepository repository,
            ShippingMethodRepository shippingMethodRepository,
            PaymentStatusRepository paymentStatusRepository,
            OrderedBookRepository orderedBookRepository,
            ModelMapper modelMapper)
    {
        super(repository, modelMapper);
        this.shippingMethodRepository = shippingMethodRepository;
        this.paymentStatusRepository = paymentStatusRepository;
        this.orderedBookRepository = orderedBookRepository;
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

    @Override
    public PurchaseDto add(PurchaseDto dto){
        dto = uploadTotalPrice(dto);
        Purchase savedEntity = repository.save(convertToEntity(dto));
        savedEntity.setVersion(0L);
        return convertToDto(savedEntity);
    }

    @Override
    public PurchaseDto uploadTotalPrice(PurchaseDto dto) {
        Purchase purchase = convertToEntity(dto);

        double totalPrice = 0.0;
        totalPrice += purchase.getShippingMethod().getPrice();

        List<OrderedBook> orderedBookList = orderedBookRepository.findByPurchaseId(purchase.getId());

        for(OrderedBook o : orderedBookList)
        {
            totalPrice += o.getBook().getPrice().doubleValue();
        }

        purchase.setPrice(totalPrice);

        return convertToDto(purchase);
    }

}

