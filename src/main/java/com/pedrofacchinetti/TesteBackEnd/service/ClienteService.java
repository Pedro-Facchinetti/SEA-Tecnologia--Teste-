package com.pedrofacchinetti.TesteBackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pedrofacchinetti.TesteBackEnd.exception.CustomConstraintViolationException;
import com.pedrofacchinetti.TesteBackEnd.exception.CustomDataIntegrityViolationException;
import com.pedrofacchinetti.TesteBackEnd.exception.ResourceNotFoundException;
import com.pedrofacchinetti.TesteBackEnd.model.Cliente;
import com.pedrofacchinetti.TesteBackEnd.model.Email;
import com.pedrofacchinetti.TesteBackEnd.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + id));
    }    

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    
    public Cliente createCliente(Cliente cliente) {
        try {
            // Estabelece a relação entre o cliente e seus emails
            if (cliente.getEmails() != null) {
                for (Email email : cliente.getEmails()) {
                    email.setCliente(cliente);
                }
            }
    
            return clienteRepository.save(cliente);
            
        } catch (DataIntegrityViolationException e) {
            // Detecta CPF duplicado
            if (e.getMessage().contains("cpf")) {
                throw new CustomDataIntegrityViolationException("CPF já cadastrado.");
            }
            throw new CustomDataIntegrityViolationException("Erro ao criar cliente devido a uma violação de integridade.");

        } catch (jakarta.validation.ConstraintViolationException e) {
            // Detecta CPF inválido
            if (e.getMessage().contains("CPF")) {
                throw new CustomConstraintViolationException("CPF inválido.");
            }
            throw new CustomConstraintViolationException("Erro de validação.");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar cliente.");
        }
    }
}