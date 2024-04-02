package com.sicredi.pauta.service.validator;

import com.sicredi.pauta.service.PautaService;
import com.sicredi.pauta.service.impl.PautaServiceImpl;
import com.sicredi.pauta.service.validator.PautaValidador;
import com.sicredi.pauta.service.validator.impl.PautaValidadorImpl;
import com.sicredi.pauta.web.rest.exception.SessaoFechadaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.sicredi.pauta.builders.pauta.PautaBuilder.umaPautaAberta;
import static com.sicredi.pauta.builders.pauta.PautaBuilder.umaPautaFechada;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class PautaValidatorImplTests {

    @Mock
    private PautaValidador pautaValidador;

    @MockBean
    private PautaService pautaService;

    @BeforeEach
    public void setUp() {
        pautaService = mock(PautaServiceImpl.class);
        pautaValidador = new PautaValidadorImpl(pautaService);
    }

    @Test
    @DisplayName("não deve lançar exceção ao validar pauta aberta")
    public void naoDeveLancarExcecaoAoValidarPautaAberta() {
        Mockito.when(pautaService.buscarPorId(any(Long.class))).thenReturn(umaPautaAberta());

        pautaValidador.validar(1L);
    }

    @Test
    @DisplayName("deve lançar uma excecao ao validar pauta fechada")
    public void deveLancarUmaExcecaoAoValidarPautaFechada(){
        Mockito.when(pautaService.buscarPorId(any(Long.class))).thenReturn(umaPautaFechada());

        Assertions.assertThrows(SessaoFechadaException.class, ()->{
           pautaValidador.validar(1L);
        });
    }
}
