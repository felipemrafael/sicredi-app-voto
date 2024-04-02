package com.sicredi.pauta.web.rest;

import com.sicredi.pauta.domain.voto.Voto;
import com.sicredi.pauta.service.VotoService;
import com.sicredi.pauta.web.rest.dto.VotoDTO;
import com.sicredi.pauta.web.rest.mapper.VotoMapper;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/votos")
@Api(value = "Votos")
public class VotoResource {

    private final VotoService votoService;

    @PostMapping(headers = "Api-Version=1")
    public ResponseEntity<Object> cadastrar(@RequestBody VotoDTO votoDTO) {
        Voto votoCadastrado = votoService.cadastrar(VotoMapper.toEntity(votoDTO));
        return new ResponseEntity<>(VotoMapper.toDto(votoCadastrado), HttpStatus.CREATED);
    }
}
