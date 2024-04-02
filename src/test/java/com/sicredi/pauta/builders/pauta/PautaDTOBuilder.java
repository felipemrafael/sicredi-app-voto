package com.sicredi.pauta.builders.pauta;

import com.sicredi.pauta.web.rest.dto.PautaDTO;

public class PautaDTOBuilder {

    public static PautaDTO umaPautaDTO(){
        return PautaDTO.builder()
                .id(1L)
                .titulo("coxinha > all")
                .build();
    }
}
