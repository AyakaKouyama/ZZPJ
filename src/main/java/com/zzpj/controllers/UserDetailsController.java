package com.zzpj.controllers;

import com.zzpj.dtos.UserDetailsDto;
import com.zzpj.entities.UserDetails;
import com.zzpj.services.interfaces.UserDetailsService;
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
@RequestMapping("/userDetails")
public class UserDetailsController extends BaseController<UserDetails, UserDetailsDto> {

    public UserDetailsController(UserDetailsService service) {
        super(service);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<UserDetailsDto>> findAll() {
        return super.findAll();
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody UserDetailsDto dto) {
        UserDetailsDto created = service.add(dto);
        return new ResponseEntity<UserDetailsDto>(created, HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id) {
        return super.delete(id);
    }
}