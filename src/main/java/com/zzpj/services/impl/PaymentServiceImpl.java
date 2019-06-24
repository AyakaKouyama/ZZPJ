package com.zzpj.services.impl;

import com.zzpj.dtos.OrderDto;
import com.zzpj.dtos.OrderResponseDto;
import com.zzpj.dtos.PayUToken;
import com.zzpj.dtos.PaymentStatusPayUDto;
import com.zzpj.services.interfaces.PaymentService;
import com.zzpj.utils.Constants;
import org.glassfish.jersey.client.ClientProperties;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Component
public class PaymentServiceImpl implements PaymentService {

    private Client client = ClientBuilder.newClient();
    private WebTarget webTarget = client.target(Constants.PAYU_URI);

    public PayUToken getToken(String credentials) {
        WebTarget authTarget = webTarget.path("/pl/standard/user/oauth/authorize");
        Invocation.Builder invocationBuilder = authTarget.request(MediaType.APPLICATION_JSON);
        return invocationBuilder.post(Entity.entity(credentials, MediaType.APPLICATION_FORM_URLENCODED_TYPE))
                .readEntity(PayUToken.class);
    }

    public OrderResponseDto createOrder(String token, String tokenType, OrderDto orderDto) {
        webTarget.property(ClientProperties.FOLLOW_REDIRECTS, Boolean.FALSE);
        WebTarget orderTarget = webTarget.path("/api/v2_1/orders");
        Invocation.Builder invocationBuilder = orderTarget.request(MediaType.APPLICATION_JSON);
        return invocationBuilder.header("Authorization", tokenType + " " + token).
                post(Entity.entity(orderDto, MediaType.APPLICATION_JSON)).readEntity(OrderResponseDto.class);
    }

    public PaymentStatusPayUDto getPaymentStatus(String orderId, String token, String tokenType) {
        WebTarget orderTarget = webTarget.path("api/v2_1/orders/" + orderId);
        Invocation.Builder invocationBuilder = orderTarget.request(MediaType.APPLICATION_JSON);
        return invocationBuilder.header("Authorization", tokenType + " " + token)
                .get()
                .readEntity(PaymentStatusPayUDto.class);
    }
}
