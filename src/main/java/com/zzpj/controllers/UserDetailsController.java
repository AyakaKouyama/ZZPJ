package com.zzpj.controllers;

import com.zzpj.dtos.UserDetailsDto;
import com.zzpj.entities.UserDetails;
import com.zzpj.services.interfaces.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userDetails")
public class UserDetailsController extends BaseController<UserDetails, UserDetailsDto> {

    public UserDetailsController(UserDetailsService service) {
        super(service);
    }
}