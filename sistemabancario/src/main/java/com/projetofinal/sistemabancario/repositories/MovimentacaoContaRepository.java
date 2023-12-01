package com.projetofinal.sistemabancario.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.sistemabancario.domain.movimentacaoConta.MovimentacaoConta;

public interface MovimentacaoContaRepository extends JpaRepository<MovimentacaoConta, UUID> {
    
}
