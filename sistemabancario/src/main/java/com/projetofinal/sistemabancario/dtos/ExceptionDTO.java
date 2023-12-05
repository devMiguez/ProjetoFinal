package com.projetofinal.sistemabancario.dtos;

import jakarta.validation.constraints.NotBlank;

public record ExceptionDTO(

    @NotBlank
    String mensagem,

    @NotBlank
    String statusCode
) {
    
}
