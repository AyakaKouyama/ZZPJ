package com.zzpj.controllers;

import com.zzpj.dtos.PaymentStatusDto;
import com.zzpj.dtos.RoleDto;
import com.zzpj.entities.Role;
import com.zzpj.services.interfaces.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleController(RoleService roleService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roleDtos = roleService.findAll()
                .stream()
                .map(role ->
                        modelMapper.map(role, RoleDto.class)
                )
                .collect(
                        Collectors.toList());
        return ResponseEntity.ok(roleDtos);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) {
        Role role = roleService.findById(id);
        RoleDto roleDto = modelMapper.map(role, RoleDto.class);
        return ResponseEntity.ok(roleDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addRole(@Valid @RequestBody RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        roleService.add(role);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        Role role  = modelMapper.map(roleDto, Role.class);
        role.setId(id);
        roleService.update(role);
        return ResponseEntity.ok().build();
    }
}