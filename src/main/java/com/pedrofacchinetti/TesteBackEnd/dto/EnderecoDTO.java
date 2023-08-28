package com.pedrofacchinetti.TesteBackEnd.dto;

public record EnderecoDTO(
    String cep,
    String logradouro,
    String bairro,
    String cidade,
    String uf,
    String complemento
) {}
