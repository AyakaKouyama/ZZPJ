package com.zzpj.controllers;

import com.zzpj.dtos.RoleDto;
import com.zzpj.entities.Role;
import com.zzpj.services.interfaces.RoleService;
import com.zzpj.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController<Role, RoleDto> {

    public RoleController(RoleService service) {
        super(service);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<RoleDto>> findAll() {
        return super.findAll();
    }


    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody RoleDto dto) {
        RoleDto created = service.add(dto);
        return new ResponseEntity<RoleDto>(created, HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<RoleDto> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id) {
        return super.delete(id);
    }
}