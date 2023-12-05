package com.projetofinal.sistemabancario.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetofinal.sistemabancario.domain.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>{
    
    //Cliente findByCpf_cnpj(String cpf_cnpj);
}
