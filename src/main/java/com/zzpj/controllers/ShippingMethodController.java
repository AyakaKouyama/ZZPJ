package com.zzpj.controllers;


import com.zzpj.dtos.ShippingMethodDto;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.services.interfaces.ShippingMethodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shippingMethods")
public class ShippingMethodController {

    private final ShippingMethodService shippingMethodService;
    private final ModelMapper modelMapper;

    @Autowired
    public ShippingMethodController(ShippingMethodService shippingMethodService, ModelMapper modelMapper) {
        this.shippingMethodService = shippingMethodService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<ShippingMethodDto>> getAllShippingMethods() {
        List<ShippingMethodDto> shippingMethodDtos = shippingMethodService.findAll()
                .stream()
                .map(shippingMethod ->
                        modelMapper.map(shippingMethod, ShippingMethodDto.class)
                )
                .collect(
                        Collectors.toList());
        return ResponseEntity.ok(shippingMethodDtos);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<ShippingMethodDto> getShippingMethodById(@PathVariable Long id) {
        ShippingMethod shippingMethod = shippingMethodService.findById(id);
        ShippingMethodDto shippingMethodDto = modelMapper.map(shippingMethod, ShippingMethodDto.class);
        return ResponseEntity.ok(shippingMethodDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addShippingMethod(@Valid @RequestBody ShippingMethodDto shippingMethodDto) {
        ShippingMethod shippingMethod = modelMapper.map(shippingMethodDto, ShippingMethod.class);
        shippingMethodService.add(shippingMethod);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateShippingMethod(@PathVariable Long id, @RequestBody ShippingMethodDto shippingMethodDto) {
        ShippingMethod shippingMethod = modelMapper.map(shippingMethodDto, ShippingMethod.class);
        shippingMethod.setId(id);
        shippingMethodService.update(shippingMethod);
        return ResponseEntity.ok().build();
    }
}
