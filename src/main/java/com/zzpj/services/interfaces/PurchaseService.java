package com.zzpj.services.interfaces;


import com.zzpj.dtos.PurchaseDto;
import com.zzpj.entities.Purchase;
import org.springframework.stereotype.Service;

public interface PurchaseService extends BaseService<Purchase, PurchaseDto> {

    public PurchaseDto add(PurchaseDto dto);
    public PurchaseDto uploadTotalPrice(PurchaseDto dto);
}
