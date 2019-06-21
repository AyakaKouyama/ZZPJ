package com.zzpj.controllers;

import com.zzpj.dtos.ShippingMethodDto;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.services.interfaces.ShippingMethodService;
import com.zzpj.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    ResponseEntity<List<ShippingMethodDto>> sortByParam(
            @RequestParam(value = "sort", required = false) String filtredFiled) {
        List<ShippingMethodDto> dtos = shippingMethodService.sortField(filtredFiled);
        return ResponseEntity.ok(dtos);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    ResponseEntity<List<ShippingMethodDto>> filterByParam(
            @RequestParam(value = "filter", required = false) String filterType,
            @RequestParam(value = "param", required = false) String param) {

        List<ShippingMethodDto> dtos = shippingMethodService.filterField(filterType, param);
        return ResponseEntity.ok(dtos);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody ShippingMethodDto dto) {
        ShippingMethodDto created = service.add(dto);
        return new ResponseEntity<ShippingMethodDto>(created, HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity update(@PathVariable Long id, @RequestBody ShippingMethodDto dto) {
        return super.update(id, dto);
    }


    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
