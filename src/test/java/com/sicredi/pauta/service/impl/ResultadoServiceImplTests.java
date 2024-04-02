package com.sicredi.pauta.service.impl;

import com.sicredi.pauta.service.PautaService;
import com.sicredi.pauta.service.ResultadoService;
import com.sicredi.pauta.web.rest.dto.ResultadoDTO;
import com.sicredi.pauta.service.impl.PautaServiceImpl;
import com.sicredi.pauta.service.impl.ResultadoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.sicredi.pauta.builders.pauta.PautaBuilder.*;
import static com.sicredi.pauta.shared.Constantes.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class ResultadoServiceImplTests {

    private ResultadoService resultadoService;

    private PautaService pautaService;

    @BeforeEach
    public void setUp() {
        pautaService = mock(PautaServiceImpl.class);
        resultadoService = new ResultadoServiceImpl(pautaService);
    }

    @Test
    @DisplayName("resultado da pauta deve ser Sim")
    public void resultadoDaPautaDeveSerSim() {
        Mockito.when(pautaService.buscarPorId(any(Long.class))).thenReturn(umaPautaComMaisVotosSim());

        ResultadoDTO resultadoDTO = resultadoService.obterResultado(1L);
        Assertions.assertEquals(resultadoDTO.getResultado(), SIM);
    }

    @Test
    @DisplayName("resultado da pauta deve ser Não")
    public void resultadoDaPautaDeveSerNao() {
        Mockito.when(pautaService.buscarPorId(any(Long.class))).thenReturn(umaPautaComMaisVotosNao());

        ResultadoDTO resultadoDTO = resultadoService.obterResultado(1L);
        Assertions.assertEquals(resultadoDTO.getResultado(), NAO);
    }

    @Test
    @DisplayName("resultado da pauta deve ser Empate")
    public void resultadoDaPautaDeveSerEmpate() {
        Mockito.when(pautaService.buscarPorId(any(Long.class))).thenReturn(umaPautaEmpatada());

        ResultadoDTO resultadoDTO = resultadoService.obterResultado(1L);
        Assertions.assertEquals(resultadoDTO.getResultado(), EMPATE);
    }
}
