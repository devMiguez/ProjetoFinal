package com.projetofinal.sistemabancario.domain.cliente;

import java.math.BigDecimal;
import java.util.UUID;

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

@Entity
@Table(name ="clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;

    private String nome;
    private String sobrenome;

    @Column(unique = true)
    private String documento;

    @Column(unique = true)
    private String email;
    private Integer senha;

    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;



}
