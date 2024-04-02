package com.sicredi.pauta.web.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SessaoFechadaException extends RuntimeException {

    public SessaoFechadaException(String exception) {
        super(exception);
    }
}
