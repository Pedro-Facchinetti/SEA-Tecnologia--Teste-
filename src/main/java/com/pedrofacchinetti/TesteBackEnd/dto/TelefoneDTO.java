package com.pedrofacchinetti.TesteBackEnd.dto;

import com.pedrofacchinetti.TesteBackEnd.enums.TipoTelefone;

public record TelefoneDTO(
    Long id,
    TipoTelefone tipo,
    String numero
) {}
