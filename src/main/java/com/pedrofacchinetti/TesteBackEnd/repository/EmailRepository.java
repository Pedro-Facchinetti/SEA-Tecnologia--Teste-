package com.pedrofacchinetti.TesteBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedrofacchinetti.TesteBackEnd.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
