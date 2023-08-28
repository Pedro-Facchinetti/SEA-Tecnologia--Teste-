package com.pedrofacchinetti.TesteBackEnd.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pedrofacchinetti.TesteBackEnd.enums.TipoTelefone;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Getter
@Setter
@Entity
@ApiModel(description = "Modelo representando um Telefone")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID único do telefone", example = "1", required = true, position = 0)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTelefone tipo;

    @NotBlank
    @ApiModelProperty(notes = "Número do telefone", example = "1234-5678", required = true, position = 2)
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @JsonBackReference
    private Cliente cliente;
}