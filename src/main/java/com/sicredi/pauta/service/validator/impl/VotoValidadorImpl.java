package com.sicredi.pauta.service.validator.impl;

import com.sicredi.pauta.domain.voto.Voto;
import com.sicredi.pauta.domain.voto.VotoPK;
import com.sicredi.pauta.repository.VotoRepository;
import com.sicredi.pauta.service.validator.CpfValidador;
import com.sicredi.pauta.service.validator.VotoValidador;
import com.sicredi.pauta.web.rest.exception.VotoDuplicadoException;
import com.sicredi.pauta.web.rest.exception.VotoInvalidoException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.sicredi.pauta.shared.Constantes.*;

@Service
@AllArgsConstructor
@Slf4j
public class VotoValidadorImpl implements VotoValidador {

    private final VotoRepository votoRepository;
    private final CpfValidador cpfValidador;

    @Override
    public void validar(Voto voto) {
        log.info("validando voto: {} ", voto);
        Optional<Voto> votoConsultado = votoRepository.findById(obterVotoId(voto));
        validar(voto.getVoto());
        if (votoConsultado.isPresent()) {
            throw new VotoDuplicadoException(VOTO_DUPLICADO_EXCEPTION);
        }
        cpfValidador.validar(voto.getCpf());
    }

    private void validar(String voto) {
        if(!(voto.equals(SIM) || voto.equals(NAO))){
            throw new VotoInvalidoException(VOTO_INVALIDO_EXCEPTION);
        }
    }

    private VotoPK obterVotoId(Voto voto) {
        return VotoPK.builder()
                .idCooperado(voto.getIdCooperado())
                .idPauta(voto.getIdPauta())
                .build();
    }


}
