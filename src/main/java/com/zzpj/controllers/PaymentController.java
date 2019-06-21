package com.zzpj.controllers;

import com.zzpj.dtos.OrderResponseDto;
import com.zzpj.services.interfaces.PurchaseService;
import com.zzpj.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PurchaseService purchaseService;

    public PaymentController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @RequestMapping(value = "/{purchaseId}/createOrder", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('" + Constants.CLIENT + "')")
    public ResponseEntity creteOrder(@PathVariable("purchaseId") Long purchaseId, HttpServletRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        String login = principal instanceof UsernamePasswordAuthenticationToken ? ((UsernamePasswordAuthenticationToken) principal)
                .getCredentials()
                .toString() : null;
        OrderResponseDto orderResponseDto = purchaseService.createOrder(purchaseId, login, request.getRemoteAddr());
        return ResponseEntity.ok(orderResponseDto);
    }

    @RequestMapping(value = "/{purchaseId}/confirmPayment", method = RequestMethod.POST)
    public ResponseEntity confirmPayment(@PathVariable("purchaseId") Long bookingId,
            String requestBody,
            @HeaderParam("OpenPayu-Signature") String payUSignature) {
        purchaseService.confirmPayment(bookingId, requestBody, payUSignature);
        return ResponseEntity.ok().build();
    }
}
