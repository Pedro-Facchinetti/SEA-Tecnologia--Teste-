package com.pedrofacchinetti.TesteBackEnd.dto;

import lombok.Getter;
import lombok.Setter;

import io.swagger.annotations.ApiModelProperty;

@Getter
@Setter
public class LoginRequest {
    
    @ApiModelProperty(value = "Nome de usuário", required = true)
    private String username;

    @ApiModelProperty(value = "Senha do usuário", required = true)
    private String password;
}


