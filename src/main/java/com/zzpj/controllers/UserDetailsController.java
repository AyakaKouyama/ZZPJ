package com.zzpj.controllers;

import com.zzpj.dtos.UserDetailsDto;
import com.zzpj.entities.UserDetails;
import com.zzpj.services.interfaces.UserDetailsService;
import com.zzpj.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/userDetails")
public class UserDetailsController extends BaseController<UserDetails, UserDetailsDto> {

    public UserDetailsController(UserDetailsService service) {
        super(service);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<UserDetailsDto>> findAll() {
        return super.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody UserDetailsDto dto) {
        UserDetailsDto created = service.add(dto);
        return new ResponseEntity<UserDetailsDto>(created, HttpStatus.CREATED);
    }


    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<UserDetailsDto> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity update(@PathVariable Long id, @RequestBody UserDetailsDto dto) {
        return super.update(id, dto);
    }


    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id){
        return super.delete(id);
    }
}