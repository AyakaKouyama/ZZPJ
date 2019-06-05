package com.zzpj.services.interfaces;


import com.zzpj.dtos.ShippingMethodDto;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.repositories.ShippingMethodRepository;
import org.springframework.stereotype.Service;

@Service
public interface ShippingMethodService extends BaseService<ShippingMethod, ShippingMethodDto> {

}

