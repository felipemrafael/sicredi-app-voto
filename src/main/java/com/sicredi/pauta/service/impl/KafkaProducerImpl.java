package com.sicredi.pauta.service.impl;

import com.sicredi.pauta.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducerImpl implements KafkaProducerService {

    public static final String TOPIC = "sicredi_group_id";

    public KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void writeMessage(String message) {
        log.info("Send message : {}", message);
        this.kafkaTemplate.send(TOPIC, message);
    }
}
