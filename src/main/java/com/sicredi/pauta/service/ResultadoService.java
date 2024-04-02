package com.sicredi.pauta.service;

import com.sicredi.pauta.web.rest.dto.ResultadoDTO;
import org.springframework.stereotype.Service;

@Service
public interface ResultadoService {
    ResultadoDTO obterResultado(Long id);
}
