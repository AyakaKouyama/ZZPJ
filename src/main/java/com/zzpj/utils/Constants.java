package com.zzpj.utils;

public interface Constants {

    String APPLICATION_HOST = "https://192.168.99.100:8080";
    String CLIENT = "CLIENT";
    String ADMINISTRATOR = "ADMINISTRATOR";
    String PAYU_CREDENTIALS = "grant_type=client_credentials&client_id=358875&client_secret=5078c1f3d440e7a8fa4f07660ae90b30";
    String DEFAULT_CURRENCY = "PLN";
    String MERCHENDANT_POS_ID = "358875";
    String CONTINUE_URL = APPLICATION_HOST + "/client/order-completed/";
    String NOTIFY_URL = APPLICATION_HOST + "/api/bookings/";
    String PAYU_URI = "https://secure.snd.payu.com";
    String PAYU_SECOND_KEY = "5078c1f3d440e7a8fa4f07660ae90b30";
    String ORDER_DESCRIPTION = "Zakup książek";
    String WAITING_FOR_PAYMENT_STATUS = "WAITING_FOR_PAYMENT";
    String PAID_STATUS = "PAID";
    String CANCELED_STATUS = "CANCELED";
}
