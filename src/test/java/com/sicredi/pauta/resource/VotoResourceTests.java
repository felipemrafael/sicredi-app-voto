package com.sicredi.pauta.resource;

import com.sicredi.pauta.domain.voto.Voto;
import com.sicredi.pauta.service.VotoService;
import com.sicredi.pauta.web.rest.PautaResource;
import com.sicredi.pauta.web.rest.VotoResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static com.sicredi.pauta.builders.voto.VotoBuilder.umVoto;
import static com.sicredi.pauta.builders.voto.VotoDTOBuilder.umVotoDTO;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {VotoResource.class})
public class VotoResourceTests {

    @Autowired
    private VotoResource votoResource;

    @MockBean
    private VotoService votoService;

    @BeforeEach
    public void setUp() {
        standaloneSetup(votoResource);
    }

    @Test
    @DisplayName("deve cadastrar voto com sucesso")
    public void deveCadastrarVotoComSucesso() {
        Mockito.when(this.votoService.cadastrar(any(Voto.class))).thenReturn(umVoto());

        given().contentType(JSON)
                .header("Api-Version", 1)
                .body(umVotoDTO())
                .when()
                .post("/votos", umVotoDTO())
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());
    }
}
