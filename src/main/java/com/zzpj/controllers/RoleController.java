package com.zzpj.controllers;

import com.zzpj.dtos.PaymentStatusDto;
import com.zzpj.dtos.RoleDto;
import com.zzpj.entities.Role;
import com.zzpj.services.interfaces.BaseService;
import com.zzpj.services.interfaces.RoleService;
import com.zzpj.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController<Role, RoleDto>{

    public RoleController(RoleService service) {
        super(service);
    }


    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<RoleDto>> findAll() {
        return super.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody RoleDto dto) {
        RoleDto created = service.add(dto);
        return new ResponseEntity<RoleDto>(created, HttpStatus.CREATED);
    }


    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<RoleDto> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity update(@PathVariable Long id, @RequestBody RoleDto dto) {
        return super.update(id, dto);
    }


    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id){
        return super.delete(id);
    }
}