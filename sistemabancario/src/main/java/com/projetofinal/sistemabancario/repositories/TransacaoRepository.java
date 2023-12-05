package com.projetofinal.sistemabancario.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.sistemabancario.domain.Transacao.TransacaoConta;

public interface TransacaoRepository extends JpaRepository<TransacaoConta, UUID> {
    
}
