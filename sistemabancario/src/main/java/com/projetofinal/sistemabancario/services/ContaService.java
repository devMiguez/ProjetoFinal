package com.projetofinal.sistemabancario.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.sistemabancario.domain.Transacao.TransacaoConta;
import com.projetofinal.sistemabancario.domain.cliente.Cliente;
import com.projetofinal.sistemabancario.domain.conta.Conta;
import com.projetofinal.sistemabancario.dtos.ContaDTO;
import com.projetofinal.sistemabancario.enums.StatusConta;
import com.projetofinal.sistemabancario.enums.TipoDaConta;
import com.projetofinal.sistemabancario.repositories.ContaRepository;

@Service
public class ContaService {
    
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteService clienteService;


    public Conta criarConta(ContaDTO contaDto) throws Exception {
        Cliente clienteDaConta = this.clienteService.findClienteByCpfCnpj(contaDto.cpfCnpjDoCliente());
    
        if (clienteDaConta == null) {
            throw new Exception("Cliente não encontrado");
        }
    
        // Se o cliente existe, continue com a criação da conta
        Conta novaConta = new Conta();
        novaConta.setCpfCnpjDoCliente(clienteDaConta);
        novaConta.setTipoDaConta(contaDto.tipoDaConta());
        novaConta.setSaldo(contaDto.saldo());
        novaConta.setStatusConta(contaDto.statusConta());
    
        // Salvar a conta no repositório
        this.salvarConta(novaConta);
    
        // Retorne a conta recém-criada ou faça o que for apropriado no seu caso
        return novaConta;
    }


    //Método para pegar todas as contas criadas
    public List<Conta> getAllContas(){
        return contaRepository.findAll();
    }


    //Método para buscar uma conta pelo id
    public Conta getContaById(UUID id){
        return this.contaRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Conta não encontrada!!"));
    }


    //Método para atualizar conta 
    public Conta atualizaConta(UUID id, ContaDTO contaDto) throws Exception {

        Conta contaExistente = contaRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Conta não encontrada"));

        // Atualizar os dados da conta com base no DTO recebido
        if (contaDto.cpfCnpjDoCliente()!= null) {
            Cliente clienteDaConta = this.clienteService.findClienteByCpfCnpj(contaDto.cpfCnpjDoCliente());
            contaExistente.setCpfCnpjDoCliente(clienteDaConta);
        }

        if (contaDto.saldo()!= null) {
            contaExistente.setSaldo(contaDto.saldo());
        }
    
        if (contaDto.statusConta()!= null) {
            contaExistente.setStatusConta(contaDto.statusConta());
        }
        
        if (contaDto.tipoDaConta()!= null) {
            contaExistente.setTipoDaConta(contaDto.tipoDaConta());
        }

        //Salvar
        return contaRepository.save(contaExistente);

        

    }

    //Método para deletar conta caso necessário
    public Conta deletaConta(UUID id) throws Exception{
        Conta contaExistente = contaRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Conta não encontrada"));

        
        contaRepository.delete(contaExistente);

        return contaExistente   ;
        
    }

    
    //Método que salva a conta no repositório
    public void salvarConta(Conta conta){
        this.contaRepository.save(conta);
    }


    //Método para pegar o extrato de determinada conta
    public List<TransacaoConta> getExtratoDaConta(UUID id){
        Conta contaExistente = contaRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Conta não encontrada"));
        
        contaExistente.getTransacoes();

        return contaExistente.getTransacoes();
    }


    //Validações para transação
    public void validaTransacao(Conta contaOrigem, BigDecimal saldo) throws Exception{

        if(contaOrigem.getTipoDaConta() != TipoDaConta.CORRENTE){  
            throw new Exception("Você não pode realizar transações da sua conta poupança!!");
        }

        if(contaOrigem.getSaldo().compareTo(saldo) < 0){
            throw new Exception("Saldo insuficente!!");
        }

        if(contaOrigem.getStatusConta() != StatusConta.ATIVA){
            throw new Exception("Essa conta não pode realizar transações, conta inativa");
        }

    }

}
