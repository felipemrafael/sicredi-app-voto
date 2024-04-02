package com.sicredi.pauta.service.validator.impl;

import com.sicredi.pauta.domain.Pauta;
import com.sicredi.pauta.service.PautaService;
import com.sicredi.pauta.service.validator.PautaValidador;
import com.sicredi.pauta.web.rest.exception.SessaoFechadaException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.sicredi.pauta.shared.Constantes.SESSAO_FECHADA_EXCEPTION;

@Service
@Slf4j
@AllArgsConstructor
public class PautaValidadorImpl implements PautaValidador {


    private final PautaService pautaService;

    @Override
    public void validar(Long idPauta) {
        log.info("validando pauta nº: {}", idPauta);
        Pauta pauta = pautaService.buscarPorId(idPauta);
        if (pauta.estahFechada()) {
            throw new SessaoFechadaException(SESSAO_FECHADA_EXCEPTION);
        }
    }

}
