package com.zzpj.services.interfaces;

import com.zzpj.dtos.ShippingMethodDto;
import com.zzpj.entities.ShippingMethod;

import java.util.List;

public interface ShippingMethodService extends BaseService<ShippingMethod, ShippingMethodDto> {

    List<ShippingMethodDto> sortField(String field);
    List<ShippingMethodDto> filterField(String field, String param);

}

