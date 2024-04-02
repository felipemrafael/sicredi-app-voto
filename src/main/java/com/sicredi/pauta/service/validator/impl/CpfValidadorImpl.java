package com.sicredi.pauta.service.validator.impl;


import com.sicredi.pauta.config.CpfConfig;
import com.sicredi.pauta.service.validator.CpfValidador;
import com.sicredi.pauta.web.rest.dto.CpfDTO;
import com.sicredi.pauta.web.rest.exception.CpfInvalidoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.sicredi.pauta.shared.Constantes.ABLE_TO_VOTE;
import static com.sicredi.pauta.shared.Constantes.CPF_INVALIDO_EXCEPTION;

@Slf4j
@Service
@RequiredArgsConstructor
public class CpfValidadorImpl implements CpfValidador {

    private final CpfConfig cpfConfig;
    private final RestTemplate restTemplate;

    @Override
    public void validar(String cpf) {
        log.info("validando CPF: " +cpf);
        CpfDTO resposta = buscarCpf(cpf);
        ehApto(resposta);
    }

    private CpfDTO buscarCpf(String cpf) {
        String uri = String.format("%s%s", this.cpfConfig.getUrl(), cpf);
        try {
            return restTemplate.getForObject(uri, CpfDTO.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CpfInvalidoException(CPF_INVALIDO_EXCEPTION);
            }
            throw new CpfInvalidoException("An error occurred while trying to access 'CpfService'.");
        }
    }

    private void ehApto(CpfDTO resposta) {
        log.info("Resposta cpfService : {}", resposta);
        if (!resposta.getStatus().equals(ABLE_TO_VOTE)) {
            throw new CpfInvalidoException(CPF_INVALIDO_EXCEPTION);
        }
    }
}
