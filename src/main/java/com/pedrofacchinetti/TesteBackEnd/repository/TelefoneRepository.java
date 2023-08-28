package com.pedrofacchinetti.TesteBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedrofacchinetti.TesteBackEnd.model.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
