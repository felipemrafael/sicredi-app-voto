package com.sicredi.pauta.builders.voto;

import com.sicredi.pauta.domain.voto.Voto;

import static com.sicredi.pauta.shared.Constantes.SIM;

public class VotoBuilder {

    public static Voto umVoto() {
        return Voto.builder()
                .cpf("10338927425")
                .idPauta(1L)
                .voto(SIM)
                .build();
    }

    public static Voto umVoto(String voto) {
        return Voto.builder()
                .cpf("10338927425")
                .idPauta(1L)
                .voto(voto)
                .build();
    }
}
