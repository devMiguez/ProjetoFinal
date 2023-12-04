package com.projetofinal.sistemabancario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.sistemabancario.domain.movimentacaoConta.MovimentacaoConta;
import com.projetofinal.sistemabancario.repositories.MovimentacaoContaRepository;

@Service
public class MovimentacaoContaService {
    
    @Autowired
    private MovimentacaoContaRepository movimentacaoContaRepository;

    @Autowired
    private ContaService contaService;

    // public MovimentacaoConta criarTransacao(){
    //     return null;
    // }
    

}
