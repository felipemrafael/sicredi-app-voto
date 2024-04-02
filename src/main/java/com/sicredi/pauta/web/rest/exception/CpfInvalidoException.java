package com.sicredi.pauta.web.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException(String exception) {
        super(exception);
    }
}
