package com.sicredi.pauta.web.rest;

import com.sicredi.pauta.service.ResultadoService;
import com.sicredi.pauta.web.rest.dto.ResultadoDTO;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/resultados")
@Api(value = "Resultado")
public class ResultadoResource {

    private final ResultadoService resultadoService;

    @GetMapping(value = "/{id}", headers = "Api-Version=1")
    public ResponseEntity<Object> obterResultado(@PathVariable Long id) {
        ResultadoDTO resultadoDTO = resultadoService.obterResultado(id);
        return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
    }
}
