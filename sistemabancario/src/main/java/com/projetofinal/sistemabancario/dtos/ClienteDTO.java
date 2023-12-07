package com.projetofinal.sistemabancario.dtos;

import com.projetofinal.sistemabancario.enums.TipoCliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteDTO(

    @NotBlank
    String nome,

    @NotBlank
    String sobrenome,

    @NotBlank
    String cpfCnpj,

    @NotBlank
    String email,

    @NotNull
    Integer senha,

    @NotNull
    TipoCliente tipoCliente

) {
    
}
