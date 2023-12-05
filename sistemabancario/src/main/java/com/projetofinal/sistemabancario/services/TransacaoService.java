package com.projetofinal.sistemabancario.services;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.sistemabancario.domain.Transacao.TransacaoConta;
import com.projetofinal.sistemabancario.domain.conta.Conta;
import com.projetofinal.sistemabancario.dtos.TransacaoDTO;
import com.projetofinal.sistemabancario.repositories.TransacaoRepository;

@Service
public class TransacaoService {
    
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaService contaService;

    //Método para realizar uma transação de uma conta para outra
    public TransacaoConta criarTransacao(TransacaoDTO data) throws Exception{
        Conta contaOrigem = contaService.getContaById(data.contaOrigem());
        Conta contaDestino = contaService.getContaById(data.contaDestino());
    
        contaService.validaTransacao(contaOrigem, data.valor());
    
        // Criar uma transação
        TransacaoConta novaTransacao = new TransacaoConta();
        novaTransacao.setContaOrigem(contaOrigem); 
        novaTransacao.setContaDestino(contaDestino); // Defina a conta de origem
        novaTransacao.setNumeroTransacao(ThreadLocalRandom.current().nextLong(100000));
        novaTransacao.setDataTransacao(LocalDateTime.now());
        novaTransacao.setValorDaTransacao(data.valor());

        //Atualizando o saldo da conta após a transação
        contaOrigem.setSaldo(contaDestino.getSaldo().subtract(data.valor()));
        contaDestino.setSaldo(contaDestino.getSaldo().add(data.valor()));
    
        transacaoRepository.save(novaTransacao);
        this.contaService.salvarConta(contaOrigem);
        this.contaService.salvarConta(contaDestino);
    
        return novaTransacao;
    }

    //Método que vai retornar todas as transações feitas:
    public List<TransacaoConta> getAllTransferencias(){
        return transacaoRepository.findAll();
    }


}
