package com.sicredi.pauta.service.validator;

import com.sicredi.pauta.config.CpfConfig;
import com.sicredi.pauta.service.validator.CpfValidador;
import com.sicredi.pauta.service.validator.impl.CpfValidadorImpl;
import com.sicredi.pauta.web.rest.dto.CpfDTO;
import com.sicredi.pauta.web.rest.exception.CpfInvalidoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static com.sicredi.pauta.builders.CpfDTOBuilder.umCpfDTOInvalido;
import static com.sicredi.pauta.builders.CpfDTOBuilder.umCpfDTOValido;
import static java.lang.String.format;
import static org.mockito.Mockito.mock;

public class CpfValidatorImplTests {

    @Mock
    private CpfValidador cpfValidador;

    @MockBean
    private RestTemplate restTemplate;

    private CpfConfig cpfConfig;

    String cpf;

    @BeforeEach
    public void setUp() {
        cpf = "16000519010";
        cpfConfig = new CpfConfig();
        cpfConfig.setUrl("https://user-info.herokuapp.com/users/");

        restTemplate = mock(RestTemplate.class);
        cpfValidador = new CpfValidadorImpl(cpfConfig, restTemplate);
    }

    @Test
    @DisplayName("cpf deve ser válido")
    public void cpfDeveSerValido() {
        var cpf = "16000519010";
        Mockito.when(this.restTemplate.getForObject(String.format("%s%s", this.cpfConfig.getUrl(), cpf), CpfDTO.class))
                .thenReturn(umCpfDTOValido());

        cpfValidador.validar(cpf);
    }

    @Test
    @DisplayName("deve lançar Exceção ao validar CPF Inválido")
    public void cpfDeveSerInvalido() {
        Mockito.when(this.restTemplate.getForObject(format(cpfConfig.getUrl(), cpf), CpfDTO.class))
                .thenReturn(umCpfDTOInvalido());

        Assertions.assertThrows(NullPointerException.class, ()->{
           cpfValidador.validar(cpf);
        });
    }



}
