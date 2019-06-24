package com.zzpj.services.interfaces;

import com.zzpj.dtos.OrderResponseDto;
import com.zzpj.dtos.PurchaseDto;
import com.zzpj.entities.Purchase;

public interface PurchaseService extends BaseService<Purchase, PurchaseDto> {

    PurchaseDto add(PurchaseDto dto, String login);
    PurchaseDto uploadTotalPrice(Long purchaseId);
    OrderResponseDto createOrder(Long bookingId, String login, String ip);
    void confirmPayment(Long bookingId, String requestBody, String payUSignature);

}
