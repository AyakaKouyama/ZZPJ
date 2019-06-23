package com.zzpj.controllers;

import com.zzpj.dtos.UserDto;
import com.zzpj.entities.User;
import com.zzpj.services.interfaces.UserService;
import com.zzpj.utils.Constants;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, UserDto> {

    private UserService userService;

    public UserController(@Lazy UserService service) {
        super(service);
        this.userService = service;
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<UserDto>> findAll() {
        return super.findAll();
    }


    @Override
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody UserDto dto) {
        UserDto created = service.add(dto);
        return new ResponseEntity<UserDto>(created, HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity update(@PathVariable Long id, @RequestBody UserDto dto) {
        return super.update(id, dto);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @RequestMapping(value ="/sort", method = RequestMethod.GET)
    ResponseEntity<List<UserDto>> sortByParam(@RequestParam(value = "sort", required = false) String filtredFiled){
        List<UserDto> dtos = userService.sortField(filtredFiled);
        return ResponseEntity.ok(dtos);
    }

    @RequestMapping(value ="/filter", method = RequestMethod.GET)
    ResponseEntity<List<UserDto>> filterByParam(@RequestParam(value = "filterType", required = false) String filterType,
                                                @RequestParam(value = "param", required = false) String param){
        List<UserDto> dtos = userService.filterField(filterType, param);

        return ResponseEntity.ok(dtos);
    }
}