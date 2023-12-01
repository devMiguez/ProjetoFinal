package com.projetofinal.sistemabancario.dtos;

import com.projetofinal.sistemabancario.domain.cliente.TipoCliente;

public record ClienteDTO(

    String nome,
    String sobrenome,
    String cpf_cnpj,
    String email,
    Integer senha,
    TipoCliente tipoCliente

) {
    
}
