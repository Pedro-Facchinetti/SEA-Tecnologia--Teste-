package com.pedrofacchinetti.TesteBackEnd.service;

import com.pedrofacchinetti.TesteBackEnd.model.Email;
import com.pedrofacchinetti.TesteBackEnd.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    public Optional<Email> findById(Long id) {
        return emailRepository.findById(id);
    }

    public Email save(Email email) {
        return emailRepository.save(email);
    }

    public void delete(Long id) {
        emailRepository.deleteById(id);
    }
}
