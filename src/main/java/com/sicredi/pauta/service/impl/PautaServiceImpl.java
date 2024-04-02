package com.sicredi.pauta.service.impl;

import com.sicredi.pauta.domain.Pauta;
import com.sicredi.pauta.repository.PautaRepository;
import com.sicredi.pauta.service.PautaService;
import com.sicredi.pauta.web.rest.dto.SessaoDTO;
import com.sicredi.pauta.web.rest.exception.PautaNaoEncontradaException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sicredi.pauta.shared.Constantes.ABERTA;
import static com.sicredi.pauta.shared.Constantes.PAUTA_NAO_ENCONTRADA_EXCEPTION;

@AllArgsConstructor
@Service
@Slf4j
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;

    @Override
    public Pauta cadastrar(Pauta pauta) {
        pauta.obterStatusFechadaCasoNulo(pauta);
        log.info("salvando pauta: {}", pauta);
        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta abrirVotacao(SessaoDTO sessaoDTO) {
        log.info("abrindo nova Sessao: {}", sessaoDTO);
        Pauta pauta = buscarPorId(sessaoDTO.getIdPauta());
        pauta.abrirVotacao(sessaoDTO);

        log.info("Salvando pauta: {}", pauta);
        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta buscarPorId(Long id) {
        log.info("Abrindo nova Pauta por id: {} ", id);
        return pautaRepository.findById(id).orElseThrow(() -> {
            throw new PautaNaoEncontradaException(PAUTA_NAO_ENCONTRADA_EXCEPTION);
        });
    }

    @Override
    public List<Pauta> consultarPautasAbertas() {
        log.info("Consultando paltas com status aberto");
        return pautaRepository.findAllByStatus(ABERTA);
    }

    @Override
    public Pauta atualizarPauta(Pauta pauta) {
        log.info("Atualizando pauta: {}", pauta);
        return this.pautaRepository.save(pauta);
    }
}
