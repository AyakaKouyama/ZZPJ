package com.zzpj.services.impl;

import com.zzpj.entities.Category;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.exceptions.EntityAlreadyExistsException;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.CategoryRepository;
import com.zzpj.repositories.ShippingMethodRepository;
import com.zzpj.services.interfaces.ShippingMethodService;
import org.springframework.beans.factory.annotation.Autowired;

public class ShippingMethodServiceImpl extends BaseServiceImpl<ShippingMethodRepository, ShippingMethod> implements ShippingMethodService {

    private final ShippingMethodRepository shippingMethodRepository;

    @Autowired
    public ShippingMethodServiceImpl(ShippingMethodRepository shippingMethodRepository) {
        super(shippingMethodRepository);
        this.shippingMethodRepository = shippingMethodRepository;
    }

    @Override
    public ShippingMethod add(ShippingMethod shippingMethod) {
        if (shippingMethodRepository.existsByName(shippingMethod.getName())) {
            throw entityAlreadyExistsException(shippingMethod.getName());
        }

        shippingMethod.setVersion(0L);
        return shippingMethodRepository.save(shippingMethod);
    }


    @Override
    public ShippingMethod findByName(String name) {
        return shippingMethodRepository.findByName(name).orElseThrow(() -> entityNotFoundException(name));
    }

    private EntityNotFoundException entityNotFoundException(String name) {
        return new EntityNotFoundException("Shipping method with name " + name + " not found.");
    }

    private EntityAlreadyExistsException entityAlreadyExistsException(String name) {
        return new EntityAlreadyExistsException("Shipping method with name " + name + " already exists.");
    }
}
