package com.projetofinal.sistemabancario.domain.cliente;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projetofinal.sistemabancario.dtos.ClienteDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "clientes")
@Table(name ="clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id_cliente;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sobrenome;

    @Column(unique = true, nullable = false)
    private String cpf_cnpj;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private Integer senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCliente tipoCliente;

    public Cliente(ClienteDTO data){
        this.nome = data.nome();
        this.sobrenome = data.sobrenome();
        this.cpf_cnpj = data.cpf_cnpj();
        this.email = data.email();
        this.senha = data.senha();
        this.tipoCliente = data.tipoCliente();
    }


}
