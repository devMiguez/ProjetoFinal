package com.projetofinal.sistemabancario.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.sistemabancario.domain.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>{
    
}
