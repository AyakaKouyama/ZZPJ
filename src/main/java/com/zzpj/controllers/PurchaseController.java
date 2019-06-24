package com.zzpj.controllers;

import com.zzpj.dtos.PurchaseDto;
import com.zzpj.entities.Purchase;
import com.zzpj.services.interfaces.PurchaseService;
import com.zzpj.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController extends BaseController<Purchase, PurchaseDto> {

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        super(purchaseService);
        this.purchaseService = purchaseService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<PurchaseDto>> findAll() {
        return super.findAll();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody PurchaseDto dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        String login = principal instanceof UsernamePasswordAuthenticationToken ? ((UsernamePasswordAuthenticationToken) principal)
                .getCredentials()
                .toString() : null;
        PurchaseDto created = purchaseService.add(dto, login);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}