package com.projetofinal.sistemabancario.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import com.projetofinal.sistemabancario.domain.cliente.Cliente;
import com.projetofinal.sistemabancario.domain.conta.StatusConta;
import com.projetofinal.sistemabancario.domain.conta.TipoDaConta;

import jakarta.validation.constraints.NotNull;

public record ContaDTO(

    @NotNull
    UUID cliente_id,

    @NotNull
    TipoDaConta tipoDaConta,

    @NotNull
    BigDecimal saldo,

    @NotNull
    StatusConta statusConta


) {
    
}
