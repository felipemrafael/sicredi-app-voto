package com.sicredi.pauta.web.rest.mapper;

import com.sicredi.pauta.domain.Pauta;
import com.sicredi.pauta.web.rest.dto.PautaDTO;

public class PautaMapper {
    public static Pauta toEntity(PautaDTO pautaDTO) {
        return Pauta.builder()
                .id(pautaDTO.getId())
                .status(pautaDTO.getStatus())
                .tempoLimite(pautaDTO.getTempoLimite())
                .titulo(pautaDTO.getTitulo())
                .build();
    }

    public static PautaDTO toDto(Pauta pauta) {
        return PautaDTO.builder()
                .id(pauta.getId())
                .status(pauta.getStatus())
                .tempoLimite(pauta.getTempoLimite())
                .titulo(pauta.getTitulo())
                .build();
    }
}
