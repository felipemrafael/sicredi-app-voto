package com.sicredi.pauta.schedule;

import com.sicredi.pauta.domain.Pauta;
import com.sicredi.pauta.service.KafkaProducerService;
import com.sicredi.pauta.service.PautaService;
import com.sicredi.pauta.service.ResultadoService;
import com.sicredi.pauta.web.rest.dto.ResultadoDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.sicredi.pauta.shared.Constantes.FECHADA;
import static com.sicredi.pauta.shared.JsonUtil.toJson;

@Component
@EnableScheduling
@Slf4j
@AllArgsConstructor
public class PautaSchedule {

    private final PautaService pautaService;
    private final ResultadoService resultadoService;
    private final KafkaProducerService kafkaProducerService;


    @Scheduled(fixedDelay = 1000)
    public void fecharPautaCasoVerdadeiro() {
        pautaService.consultarPautasAbertas().stream()
                .filter(Pauta::estahFechadaIhNaoFoiEnviada).forEach(pauta -> {
            ResultadoDTO resultadoDTO = resultadoService.obterResultado(pauta.getId());
            atulizarResultado(resultadoDTO);
            log.info("Enviando resultado : {}", resultadoDTO);
            kafkaProducerService.writeMessage(toJson(resultadoDTO));

            log.info("salvando pauta fechada : {}", pauta);
            sinalizarEnvioPauta(pauta);
            pautaService.atualizarPauta(pauta);
        });
    }

    private void atulizarResultado(ResultadoDTO resultadoDTO) {
        resultadoDTO.setStatus(FECHADA);
    }

    private void sinalizarEnvioPauta(Pauta pauta) {
        pauta.setEnviadoKafka(true);
    }
}
