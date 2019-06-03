package com.zzpj.services.interfaces;


import com.zzpj.entities.ShippingMethod;
import com.zzpj.repositories.ShippingMethodRepository;
import org.springframework.stereotype.Service;

@Service
public interface ShippingMethodService extends BaseService<ShippingMethodRepository, ShippingMethod> {

    ShippingMethod add(ShippingMethod category);

    ShippingMethod findByName(String name);
}

