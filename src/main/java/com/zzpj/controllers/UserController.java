package com.zzpj.controllers;

import com.zzpj.dtos.UserDto;
import com.zzpj.entities.*;
import com.zzpj.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, UserDto> {

    public UserController(@Lazy UserService service) {
        super(service);
    }
}