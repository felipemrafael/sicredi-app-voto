package com.sicredi.pauta.service.impl;

import com.sicredi.pauta.domain.voto.Voto;
import com.sicredi.pauta.domain.voto.VotoPK;
import com.sicredi.pauta.repository.VotoRepository;
import com.sicredi.pauta.service.VotoService;
import com.sicredi.pauta.service.validator.PautaValidador;
import com.sicredi.pauta.service.validator.VotoValidador;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final VotoValidador votoValidador;
    private final PautaValidador pautaValidador;

    @Override
    public Optional<Voto> buscarPorId(Voto voto){
        return votoRepository.findById(obterVotoId(voto));
    }

    @Override
    public Voto cadastrar(Voto voto) {
        votoValidador.validar(voto);
        pautaValidador.validar(voto.getIdPauta());
        log.info("cadastrando novo voto: " + voto);
        return votoRepository.save(voto);
    }

    private VotoPK obterVotoId(Voto voto) {
        return VotoPK.builder()
                .idCooperado(1L)
                .idPauta(voto.getIdPauta())
                .build();
    }
}
