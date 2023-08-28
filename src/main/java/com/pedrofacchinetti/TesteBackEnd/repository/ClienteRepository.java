package com.pedrofacchinetti.TesteBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pedrofacchinetti.TesteBackEnd.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}