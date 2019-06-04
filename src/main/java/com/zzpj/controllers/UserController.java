package com.zzpj.controllers;

import com.zzpj.dtos.UserDto;
import com.zzpj.entities.Role;
import com.zzpj.entities.User;
import com.zzpj.entities.UserDetails;
import com.zzpj.services.interfaces.RoleService;
import com.zzpj.services.interfaces.UserDetailsService;
import com.zzpj.services.interfaces.UserService;
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
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private UserDetailsService userDetailsService;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService,
            RoleService roleService,
            UserDetailsService userDetailsService,
            ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtoList = userService.findAll()
                .stream()
                .map(book ->
                        modelMapper.map(book, UserDto.class)
                )
                .collect(
                        Collectors.toList());
        return ResponseEntity.ok(userDtoList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserDto result = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addUser(@Valid @RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        Role role = roleService.findById(userDto.getRole().getId());
        UserDetails userDetails = userDetailsService.findById(userDto.getUserDetails().getId());
        user.setRole(role);
        user.setUserDetails(userDetails);
        userService.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        Role role = roleService.findById(userDto.getRole().getId());
        UserDetails userDetails = userDetailsService.findById(userDto.getUserDetails().getId());
        user.setId(id);
        user.setRole(role);
        user.setUserDetails(userDetails);
        userService.update(user);
        return ResponseEntity.ok(user);
    }
}