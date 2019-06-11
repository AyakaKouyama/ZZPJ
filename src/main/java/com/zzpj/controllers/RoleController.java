package com.zzpj.controllers;

import com.zzpj.dtos.PaymentStatusDto;
import com.zzpj.dtos.RoleDto;
import com.zzpj.entities.Role;
import com.zzpj.services.interfaces.BaseService;
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
public class RoleController extends BaseController<Role, RoleDto>{

    public RoleController(RoleService service) {
        super(service);
    }
}