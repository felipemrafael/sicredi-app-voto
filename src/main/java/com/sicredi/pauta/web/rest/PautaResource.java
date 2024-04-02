package com.sicredi.pauta.web.rest;

import com.sicredi.pauta.domain.Pauta;
import com.sicredi.pauta.service.PautaService;
import com.sicredi.pauta.web.rest.dto.PautaDTO;
import com.sicredi.pauta.web.rest.dto.SessaoDTO;
import com.sicredi.pauta.web.rest.mapper.PautaMapper;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sicredi.pauta.web.rest.mapper.PautaMapper.toDto;

@RestController
@AllArgsConstructor
@RequestMapping("/pauta")
@Api(value = "Pauta")
public class PautaResource {

    private final PautaService pautaService;

    @PostMapping(headers = "Api-Version=1")
    public ResponseEntity<Object> cadastrar(@RequestBody PautaDTO pautaDTO) {
        Pauta pautaCadastrada = pautaService.cadastrar(PautaMapper.toEntity(pautaDTO));
        return new ResponseEntity<>(toDto(pautaCadastrada), HttpStatus.CREATED);
    }

    @PostMapping(value = "/abrir", headers = "Api-Version=1")
    public ResponseEntity<Object> abrirVotacao(@RequestBody SessaoDTO sessaoDTO) {
        Pauta pautaAberta = pautaService.abrirVotacao(sessaoDTO);
        return new ResponseEntity<>(toDto(pautaAberta), HttpStatus.ACCEPTED);
    }
}
