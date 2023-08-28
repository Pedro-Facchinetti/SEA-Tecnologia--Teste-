package com.pedrofacchinetti.TesteBackEnd.Controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateCliente() throws Exception {
        // Preparar dados
        String clienteJson = "{"
            + "\"nome\": \"Pedro Teste\","
            + "\"cpf\": \"12345678901\","
            + "\"cep\": \"12345678\","
            + "\"logradouro\": \"Rua de Teste\","
            + "\"bairro\": \"Bairro de Teste\","
            + "\"cidade\": \"Cidade de Teste\","
            + "\"uf\": \"SP\","
            + "\"complemento\": \"Apto 123\","
            + "\"telefones\": ["
                + "{"
                    + "\"tipo\": \"RESIDENCIAL\","
                    + "\"numero\": \"1122334455\""
                + "},"
                + "{"
                    + "\"tipo\": \"CELULAR\","
                    + "\"numero\": \"9876543210\""
                + "}"
            + "],"
            + "\"emails\": ["
                + "{"
                    + "\"valor\": \"teste@email.com\""
                + "},"
                + "{"
                    + "\"valor\": \"outroteste@email.com\""
                + "}"
            + "]"
        + "}";

        // Executar o teste
        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clienteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Pedro Teste"))
                .andExpect(jsonPath("$.cpf").value("12345678901"));
    }

}
