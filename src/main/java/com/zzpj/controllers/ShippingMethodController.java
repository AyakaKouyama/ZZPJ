package com.zzpj.controllers;

import com.zzpj.dtos.ShippingMethodDto;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.services.interfaces.ShippingMethodService;
import com.zzpj.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shippingMethods")
public class ShippingMethodController extends BaseController<ShippingMethod, ShippingMethodDto> {

    @Autowired
    public ShippingMethodController(ShippingMethodService service) {
        super(service);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<ShippingMethodDto>> findAll() {
        return super.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody ShippingMethodDto dto) {
        ShippingMethodDto created = service.add(dto);
        return new ResponseEntity<ShippingMethodDto>(created, HttpStatus.CREATED);
    }


    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<ShippingMethodDto> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity update(@PathVariable Long id, @RequestBody ShippingMethodDto dto) {
        return super.update(id, dto);
    }


    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id){
        return super.delete(id);
    }
}
