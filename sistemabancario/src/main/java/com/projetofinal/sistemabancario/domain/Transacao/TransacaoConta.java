package com.projetofinal.sistemabancario.domain.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.projetofinal.sistemabancario.domain.conta.Conta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "transacoes")
@Table(name = "transacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TransacaoConta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_conta_origem")
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "id_conta_destino")
    private Conta contaDestino;

    @Column(unique = true, nullable = false)
    private Long numeroTransacao;

    @Column(nullable = false)
    private LocalDateTime dataTransacao;

    @Column(nullable = false)
    private BigDecimal valorDaTransacao;





}
