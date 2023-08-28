package com.pedrofacchinetti.TesteBackEnd.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Embeddable
@ApiModel(description = "Modelo representando um Endereço")
@Getter
@Setter
public class Endereco {

    @ApiModelProperty(notes = "CEP do endereço", example = "12345-678", required = true, position = 0)
    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;
    @ApiModelProperty(notes = "Complemento do endereço", example = "Apartamento 101", position = 6)
    private String complemento;

    @Override
public String toString() {
    return "Endereco [cep=" + cep + ", logradouro=" + logradouro + ", bairro=" + bairro + ", cidade=" + cidade
            + ", uf=" + uf + ", complemento=" + complemento + "]";
}

}

