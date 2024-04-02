package com.sicredi.pauta.web.rest.mapper;

import com.sicredi.pauta.domain.voto.Voto;
import com.sicredi.pauta.web.rest.dto.VotoDTO;

public class VotoMapper {
    public static Voto toEntity(VotoDTO votoDTO) {
        return Voto.builder()
                .cpf(votoDTO.getCpf())
                .idPauta(votoDTO.getIdPauta())
                .idCooperado(votoDTO.getIdCooperado())
                .voto(votoDTO.getVoto())
                .build();
    }

    public static VotoDTO toDto(Voto voto) {
        return VotoDTO.builder()
                .cpf(voto.getCpf())
                .idPauta(voto.getIdPauta())
                .idCooperado(voto.getIdCooperado())
                .voto(voto.getVoto())
                .build();
    }
}
