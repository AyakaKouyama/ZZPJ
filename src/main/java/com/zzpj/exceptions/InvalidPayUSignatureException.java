package com.zzpj.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPayUSignatureException extends RuntimeException {

    public InvalidPayUSignatureException(String message){
        super(message);
    }
}
