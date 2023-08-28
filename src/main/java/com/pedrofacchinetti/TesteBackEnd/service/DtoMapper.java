package com.pedrofacchinetti.TesteBackEnd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pedrofacchinetti.TesteBackEnd.dto.ClienteDTO;
import com.pedrofacchinetti.TesteBackEnd.dto.EmailDTO;
import com.pedrofacchinetti.TesteBackEnd.dto.TelefoneDTO;
import com.pedrofacchinetti.TesteBackEnd.model.Cliente;
import com.pedrofacchinetti.TesteBackEnd.model.Email;
import com.pedrofacchinetti.TesteBackEnd.model.Telefone;

@Service
public class DtoMapper {

    public ClienteDTO toClienteDTO(Cliente cliente) {
        List<TelefoneDTO> telefoneDTOs = cliente.getTelefones().stream()
                                                 .map(this::toTelefoneDTO)
                                                 .collect(Collectors.toList());
        
        List<EmailDTO> emailDTOs = cliente.getEmails().stream()
                                               .map(this::toEmailDTO)
                                               .collect(Collectors.toList());
        
        return new ClienteDTO(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getEndereco(),
            telefoneDTOs,
            emailDTOs
        );
    }

    public TelefoneDTO toTelefoneDTO(Telefone telefone) {
        return new TelefoneDTO(
            telefone.getId(),
            telefone.getTipo(),
            telefone.getNumero()
        );
    }

    public EmailDTO toEmailDTO(Email email) {
        return new EmailDTO(
            email.getId(),
            email.getValor()
        );
    }
}

