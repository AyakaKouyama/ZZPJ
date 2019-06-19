package com.zzpj.controllers;

import com.zzpj.security.JwtAuthenticationResponse;
import com.zzpj.security.JwtTokenProvider;
import com.zzpj.security.LoginRequest;
import com.zzpj.security.UserAuthServiceImpl;
import com.zzpj.services.interfaces.UserService;
import com.zzpj.utils.Constants;
import org.jboss.logging.annotations.Pos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@PermitAll
public class AuthController {

    private final UserService userService;

    private final JwtTokenProvider tokenProvider;

    private final ModelMapper modelMapper;

    private final UserAuthServiceImpl userAuthService;

    @Autowired
    public AuthController(UserService userService, JwtTokenProvider tokenProvider, ModelMapper modelMapper, UserAuthServiceImpl userAuthService){
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.modelMapper = modelMapper;
        this.userAuthService = userAuthService;
    }

    @PostMapping("/test2")
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    public ResponseEntity test2(){
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
    }

    @PostMapping("/test")
    @PreAuthorize("hasAuthority('" + Constants.CLIENT +"')")
    public ResponseEntity test(){
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
    }

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = userService.authenticate(loginRequest.getLogin(), loginRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setAccessToken(jwt);
        return ResponseEntity.ok(response);
    }

}
