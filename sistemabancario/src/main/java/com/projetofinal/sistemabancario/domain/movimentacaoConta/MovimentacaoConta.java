package com.projetofinal.sistemabancario.domain.movimentacaoConta;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import com.projetofinal.sistemabancario.domain.conta.Conta;

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

@Entity(name = "movimentacaoConta")
@Table(name = "movimentacaoConta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MovimentacaoConta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_conta")
    private Conta conta;

    private Integer numeroDaTransacao;

    private Date dataDaTransacao;

    private TipoDaTransacao tipoDaTransacao;

    private BigDecimal valorDaTransacao;    




}
