package com.projetofinal.sistemabancario.services;

import org.springframework.beans.factory.annotation.Autowired;
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

    

    //Método que salva a conta no repositório
    public void salvarConta(Conta conta){
        this.contaRepository.save(conta);
    }

}
