package com.pedrofacchinetti.TesteBackEnd.SecurityIntegrationTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedrofacchinetti.TesteBackEnd.dto.LoginResponse;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAccessWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testPadraoUserAccessToAdminResource() throws Exception {
        String token = authenticateUser("Padrão", "123qwe123");

        mockMvc.perform(delete("/api/clientes/1")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    private String authenticateUser(String username, String password) throws Exception {
        String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
        MvcResult result = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        // Parse a resposta e extraia o token.
        ObjectMapper mapper = new ObjectMapper();
        LoginResponse loginResponse = mapper.readValue(response, LoginResponse.class);
        return loginResponse.getToken();
    }

    @Test
    public void testAdminUserAccessToAdminResource() throws Exception {
        String token = authenticateUser("Admin", "123qwel@#");

        mockMvc.perform(get("/api/admin-endpoint") // substitua pelo caminho correto do recurso exclusivo do ADMIN
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void testAccessWithoutToken() throws Exception {
        mockMvc.perform(get("/api/clientes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden()); // 403 Forbidden
    }

}