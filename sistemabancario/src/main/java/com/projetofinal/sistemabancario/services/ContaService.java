package com.projetofinal.sistemabancario.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetofinal.sistemabancario.domain.cliente.Cliente;
import com.projetofinal.sistemabancario.domain.conta.Conta;
import com.projetofinal.sistemabancario.dtos.ContaDTO;
import com.projetofinal.sistemabancario.repositories.ContaRepository;

@Service
public class ContaService {
    
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteService clienteService;

    //Cria uma nova conta
    public Conta criarConta(ContaDTO contaDto) throws Exception{
        Cliente clienteDaConta = this.clienteService.findClienteById(contaDto.cliente_id());

        if(clienteDaConta == null){
            throw new Exception("cliente não encontrado");
        }

        // Se o cliente existe, continue com a criação da conta
        Conta novaConta = new Conta();
        novaConta.setCliente(clienteDaConta);
        novaConta.setTipoDaConta(contaDto.tipoDaConta());
        novaConta.setSaldo(contaDto.saldo());
        novaConta.setStatusConta(contaDto.statusConta());

        // Salve a conta no repositório
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
        if (contaDto.cliente_id()!= null) {
            Cliente clienteDaConta = this.clienteService.findClienteById(contaDto.cliente_id());
            contaExistente.setCliente(clienteDaConta);
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

    
    //Método que salva a conta no repositório
    public void salvarConta(Conta conta){
        this.contaRepository.save(conta);
    }

}
