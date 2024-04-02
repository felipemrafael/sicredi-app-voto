package com.sicredi.pauta.web.rest.exception;

import com.sicredi.pauta.web.rest.VotoResource;
import com.sicredi.pauta.web.rest.dto.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = CpfInvalidoException.class)
    protected ResponseEntity<ErroDTO> handleCpfBusinessException(CpfInvalidoException ex){
        return new ResponseEntity<>(new ErroDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ErroDTO> handleBusinessException(Exception ex){
        return new ResponseEntity<>(new ErroDTO(ex.getMessage()), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(value = PautaNaoEncontradaException.class)
    protected ResponseEntity<ErroDTO> handleNotFoundException(PautaNaoEncontradaException ex){
        return new ResponseEntity<>(new ErroDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SessaoFechadaException.class)
    protected ResponseEntity<ErroDTO> handleIntegrationException(SessaoFechadaException ex){
        return new ResponseEntity<>(new ErroDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = VotoInvalidoException.class)
    protected ResponseEntity<ErroDTO> handleIntegrationException(VotoInvalidoException ex){
        return new ResponseEntity<>(new ErroDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = VotoDuplicadoException.class)
    protected ResponseEntity<ErroDTO> handleIntegrationException(VotoDuplicadoException ex){
        return new ResponseEntity<>(new ErroDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
