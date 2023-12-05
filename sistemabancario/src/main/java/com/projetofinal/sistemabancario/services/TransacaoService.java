package com.projetofinal.sistemabancario.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.sistemabancario.domain.Transacao.TransacaoConta;
import com.projetofinal.sistemabancario.domain.cliente.Cliente;
import com.projetofinal.sistemabancario.domain.conta.Conta;
import com.projetofinal.sistemabancario.dtos.TransacaoDTO;
import com.projetofinal.sistemabancario.repositories.TransacaoRepository;

@Service
public class TransacaoService {
    
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaService contaService;

    @Autowired
    private EmailService emailService;


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

        // Pegar as informações do cliente associado à conta de origem
        Cliente clienteOrigem = contaOrigem.getCliente();
        String emailClienteOrigem = clienteOrigem.getEmail();
        String nomeClienteOrigem = clienteOrigem.getNome();

        // Pegar as informações do cliente associado à conta de destino
        Cliente clienteDestino = contaDestino.getCliente();
        String emailClienteDestino = clienteDestino.getEmail();
        String nomeClienteDestino = clienteDestino.getNome();

        //Atualizando o saldo da conta após a transação
        contaOrigem.setSaldo(contaDestino.getSaldo().subtract(data.valor()));
        contaDestino.setSaldo(contaDestino.getSaldo().add(data.valor()));
    
        //Salvando a transação e as mudanças nas contas origem e destino
        transacaoRepository.save(novaTransacao);
        this.contaService.salvarConta(contaOrigem);
        this.contaService.salvarConta(contaDestino);

        //Enviando um email para o cliente de origem e o cliente de destino
        emailService.enviarEmail(emailClienteOrigem, "Transferência de Saldo", "Transferência de " + data.valor() + " para " + nomeClienteDestino + "" + " do email: " + " " + emailClienteDestino);
        emailService.enviarEmail(emailClienteDestino, "Recebimento de Transferência", "Recebimento de " + data.valor() + " de " + nomeClienteOrigem + "" + " do email: " + " " + emailClienteOrigem);

        return novaTransacao;
    }

    //Método que vai retornar todas as transações feitas:
    public List<TransacaoConta> getAllTransferencias(){
        return transacaoRepository.findAll();
    }


}
