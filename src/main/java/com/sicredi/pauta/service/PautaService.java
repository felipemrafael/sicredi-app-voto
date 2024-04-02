package com.sicredi.pauta.service;

import com.sicredi.pauta.domain.Pauta;
import com.sicredi.pauta.web.rest.dto.SessaoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PautaService {
    Pauta cadastrar(Pauta toEntity);

    Pauta abrirVotacao(SessaoDTO sessaoDTO);

    Pauta buscarPorId(Long id);

    List<Pauta> consultarPautasAbertas();

    Pauta atualizarPauta(Pauta pauta);
}
