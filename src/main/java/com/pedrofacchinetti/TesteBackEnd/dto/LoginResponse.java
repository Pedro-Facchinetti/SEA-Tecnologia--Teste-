package com.pedrofacchinetti.TesteBackEnd.dto;

import lombok.Getter;
import lombok.Setter;

import io.swagger.annotations.ApiModelProperty;

@Getter
@Setter
public class LoginResponse {

    @ApiModelProperty(value = "Nome de usuário", required = true)
    private String username;
    
    @ApiModelProperty(value = "Senha do usuário", required = true)
    private String role;
    
    @ApiModelProperty(value = "Token do usuário", required = true)
    private String token;

    public LoginResponse(String username, String role, String token) {
        this.username = username;
        this.role = role;
        this.token = token;
    }
}

