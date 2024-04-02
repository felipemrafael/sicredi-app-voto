package com.sicredi.pauta.service;

import org.springframework.stereotype.Service;

@Service
public interface KafkaProducerService {
    void writeMessage(String message);
}
