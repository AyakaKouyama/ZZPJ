package com.zzpj.services.interfaces;

import com.zzpj.dtos.PaymentStatusDto;
import com.zzpj.entities.PaymentStatus;

public interface PaymentStatusService extends BaseService<PaymentStatus, PaymentStatusDto> {

    PaymentStatus getPaymentStatusByName(String name);

}
