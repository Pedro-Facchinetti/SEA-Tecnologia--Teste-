package com.pedrofacchinetti.TesteBackEnd.dto;

import java.util.List;

import com.pedrofacchinetti.TesteBackEnd.model.Endereco;

public record ClienteDTO(
    Long id,
    String nome,
    String cpf,
    Endereco endereco,
    List<TelefoneDTO> telefones,
    List<EmailDTO> emails
) {}

