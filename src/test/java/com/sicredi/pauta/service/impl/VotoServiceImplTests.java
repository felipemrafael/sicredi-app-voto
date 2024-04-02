package com.sicredi.pauta.service.impl;

import com.sicredi.pauta.domain.voto.Voto;
import com.sicredi.pauta.domain.voto.VotoPK;
import com.sicredi.pauta.repository.VotoRepository;
import com.sicredi.pauta.service.VotoService;
import com.sicredi.pauta.service.validator.PautaValidador;
import com.sicredi.pauta.service.validator.VotoValidador;
import com.sicredi.pauta.service.impl.VotoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.sicredi.pauta.builders.voto.VotoBuilder.umVoto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class VotoServiceImplTests {

    private VotoService votoService;

    private VotoRepository votoRepository;

    private VotoValidador votoValidador;

    private PautaValidador pautaValidador;

    private Voto voto;

    @BeforeEach
    public void setUp() {
        voto = umVoto();
        votoValidador = mock(VotoValidador.class);
        pautaValidador = mock(PautaValidador.class);
        votoRepository = mock(VotoRepository.class);
        votoService = new VotoServiceImpl(votoRepository, votoValidador, pautaValidador);
    }

    @Test
    @DisplayName("deve cadastrar voto")
    public void deveCadastrarVotoComSucesso() {
        Mockito.when(votoRepository.save(any(Voto.class))).thenReturn(voto);

        Voto esperado = votoService.cadastrar(umVoto());

        Assertions.assertEquals(esperado.getCpf(), voto.getCpf());
        Assertions.assertEquals(esperado.getVoto(), voto.getVoto());
        Assertions.assertEquals(esperado.getIdPauta(), voto.getIdPauta());
    }

    @Test
    @DisplayName("deve buscar voto por id")
    public void deveBuscarVotoPorId() {
        Mockito.when(votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.of(umVoto()));

        Optional<Voto> esperado = votoService.buscarPorId(umVoto());

        Assertions.assertTrue(esperado.isPresent());
    }
}
