package com.projetofinal.sistemabancario.domain.cliente;

import java.math.BigDecimal;
import java.util.UUID;

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

    private String nome;
    private String sobrenome;

    @Column(unique = true)
    private String cpf_cnpj;

    @Column(unique = true)
    private String email;
    private Integer senha;

    @Enumerated(EnumType.STRING)
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
