package com.sicredi.pauta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.sicredi.pauta.*" })
@EntityScan("com.sicredi.pauta.*")
public class PautaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PautaApiApplication.class, args);
    }

}
