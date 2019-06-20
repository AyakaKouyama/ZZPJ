package com.zzpj.controllers;

import com.zzpj.dtos.BookDto;
import com.zzpj.dtos.ShippingMethodDto;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.services.interfaces.ShippingMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shippingMethods")
public class ShippingMethodController extends BaseController<ShippingMethod, ShippingMethodDto> {

    ShippingMethodService shippingMethodService;

    @Autowired
    public ShippingMethodController(ShippingMethodService service) {
        super(service);
        this.shippingMethodService = service;
    }

    @RequestMapping(value ="/sort", method = RequestMethod.GET)
    ResponseEntity<List<ShippingMethodDto>> sortByParam(@RequestParam(value = "sort", required = false) String filtredFiled){
        List<ShippingMethodDto> dtos = shippingMethodService.sortField(filtredFiled);
        return ResponseEntity.ok(dtos);
    }

    @RequestMapping(value ="/filter", method = RequestMethod.GET)
    ResponseEntity<List<ShippingMethodDto>> filterByParam(@RequestParam(value = "filter", required = false) String filterType,
                                                @RequestParam(value = "param", required = false) String param){
        List<ShippingMethodDto> dtos = shippingMethodService.filterField(filterType, param);

        return ResponseEntity.ok(dtos);
    }
}
