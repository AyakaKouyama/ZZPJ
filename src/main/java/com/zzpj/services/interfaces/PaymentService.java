package com.zzpj.services.interfaces;

import com.zzpj.dtos.OrderDto;
import com.zzpj.dtos.OrderResponseDto;
import com.zzpj.dtos.PayUToken;
import com.zzpj.dtos.PaymentStatusPayUDto;

public interface PaymentService {

    PayUToken getToken(String credentials);

    OrderResponseDto createOrder(String token, String tokenType, OrderDto orderDto);

    PaymentStatusPayUDto getPaymentStatus(String orderId, String token, String tokenType);
}
