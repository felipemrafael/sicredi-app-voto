package com.sicredi.pauta.service;

import com.sicredi.pauta.domain.voto.Voto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VotoService {
    Optional<Voto> buscarPorId(Voto voto);

    Voto cadastrar(Voto voto);
}
