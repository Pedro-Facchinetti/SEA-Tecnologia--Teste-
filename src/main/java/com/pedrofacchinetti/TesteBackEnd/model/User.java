package com.pedrofacchinetti.TesteBackEnd.model;

import com.pedrofacchinetti.TesteBackEnd.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Setter
@Getter
@Entity
@ApiModel(description = "Modelo representando um Usuário")
public class User {

    @Id
    @ApiModelProperty(notes = "ID único do usuário", example = "1", required = true, position = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @ApiModelProperty(notes = "Função do usuário", example = "ADMIN", required = true, position = 3)
    @Enumerated(EnumType.STRING)
    private UserRole role;
}

