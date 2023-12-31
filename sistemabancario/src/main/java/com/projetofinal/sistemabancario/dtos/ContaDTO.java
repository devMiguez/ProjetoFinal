package com.projetofinal.sistemabancario.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import com.projetofinal.sistemabancario.domain.cliente.Cliente;
import com.projetofinal.sistemabancario.enums.StatusConta;
import com.projetofinal.sistemabancario.enums.TipoDaConta;

import jakarta.validation.constraints.NotNull;

public record ContaDTO(

    @NotNull
    String cpfCnpjDoCliente,

    @NotNull
    TipoDaConta tipoDaConta,

    @NotNull
    BigDecimal saldo,

    @NotNull
    StatusConta statusConta


) {
    
}
