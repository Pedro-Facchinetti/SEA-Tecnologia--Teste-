package com.pedrofacchinetti.TesteBackEnd.service;

import com.pedrofacchinetti.TesteBackEnd.model.Telefone;
import com.pedrofacchinetti.TesteBackEnd.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    public List<Telefone> findAll() {
        return telefoneRepository.findAll();
    }

    public Optional<Telefone> findById(Long id) {
        return telefoneRepository.findById(id);
    }

    public Telefone save(Telefone telefone) {
        return telefoneRepository.save(telefone);
    }

    public void delete(Long id) {
        telefoneRepository.deleteById(id);
    }
}
