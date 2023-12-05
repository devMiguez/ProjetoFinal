package com.projetofinal.sistemabancario.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record TransacaoDTO(

    @NotNull
    UUID contaOrigem,

    @NotNull
    UUID contaDestino,

    @NotNull
    BigDecimal valor

) {
    
}
