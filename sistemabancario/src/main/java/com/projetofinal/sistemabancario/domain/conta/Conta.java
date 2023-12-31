package com.projetofinal.sistemabancario.domain.conta;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projetofinal.sistemabancario.domain.Transacao.TransacaoConta;
import com.projetofinal.sistemabancario.domain.cliente.Cliente;
import com.projetofinal.sistemabancario.enums.StatusConta;
import com.projetofinal.sistemabancario.enums.TipoDaConta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "contas")
@Table(name = "contas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_conta;

    @ManyToOne
    @JoinColumn(name = "CpfCnpjDoCliente")
    @JsonBackReference
    private Cliente cpfCnpjDoCliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDaConta tipoDaConta;

    @Column(nullable = false)
    private BigDecimal saldo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusConta statusConta;


    //mappedby
    @OneToMany(mappedBy = "contaDestino")
    @JsonManagedReference
    private List<TransacaoConta> transacoes;




}
