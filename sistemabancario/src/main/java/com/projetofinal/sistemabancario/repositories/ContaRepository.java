package com.projetofinal.sistemabancario.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.sistemabancario.domain.conta.Conta;

public interface ContaRepository extends JpaRepository<Conta, UUID>{
    
}
