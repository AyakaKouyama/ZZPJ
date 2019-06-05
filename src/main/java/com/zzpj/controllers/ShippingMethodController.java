package com.zzpj.controllers;

import com.zzpj.dtos.ShippingMethodDto;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.services.interfaces.ShippingMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shippingMethods")
public class ShippingMethodController extends BaseController<ShippingMethod, ShippingMethodDto> {

    @Autowired
    public ShippingMethodController(ShippingMethodService service) {
        super(service);
    }
}
