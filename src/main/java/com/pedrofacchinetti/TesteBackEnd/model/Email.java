package com.pedrofacchinetti.TesteBackEnd.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modelo representando um Email")
@Entity
@Getter
@Setter
public class Email {

    @ApiModelProperty(notes = "ID único do email", example = "1", required = true, position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "Valor do email", example = "exemplo@email.com", required = true, position = 1)
    @NotBlank
    @jakarta.validation.constraints.Email
    private String valor;

    @ApiModelProperty(notes = "Cliente associado ao email", required = true, position = 2)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @JsonBackReference
    private Cliente cliente;
}

