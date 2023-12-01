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

    //Cria uma nova conta
    public Conta criarConta(ContaDTO contaDto) {
        var contaNova = new Conta(contaDto);
        this.salvarConta(contaNova);
        return contaNova;
    }

    //Método que salva a conta no repositório
    public void salvarConta(Conta conta){
        this.contaRepository.save(conta);
    }

}
