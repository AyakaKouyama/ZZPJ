package com.zzpj.controllers;

import com.zzpj.dtos.UserDetailsDto;
import com.zzpj.entities.UserDetails;
import com.zzpj.services.interfaces.UserDetailsService;
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
@RequestMapping("/userDetails")
public class UserDetailsController {

    private UserDetailsService userDetailsService;
    private ModelMapper modelMapper;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService, ModelMapper modelMapper) {
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<UserDetailsDto>> getAllDetails() {
        List<UserDetailsDto> UserDetailsDtoList = userDetailsService.findAll()
                .stream()
                .map(userDetails ->
                        modelMapper.map(userDetails, UserDetailsDto.class)
                )
                .collect(
                        Collectors.toList());
        return ResponseEntity.ok(UserDetailsDtoList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<UserDetailsDto> getUserDetailsById(@PathVariable Long id) {
        UserDetails userDetails = userDetailsService.findById(id);
        UserDetailsDto result = modelMapper.map(userDetails, UserDetailsDto.class);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addUserDetails(@Valid @RequestBody UserDetailsDto userDetailsDto) {
        UserDetails userDetails = modelMapper.map(userDetailsDto, UserDetails.class);
        userDetailsService.add(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateUserDetails(@PathVariable Long id, @RequestBody UserDetailsDto userDetailsDto) {
        UserDetails userDetails = modelMapper.map(userDetailsDto, UserDetails.class);
        userDetails.setId(id);
        userDetailsService.update(userDetails);
        return ResponseEntity.ok(userDetails);
    }
}