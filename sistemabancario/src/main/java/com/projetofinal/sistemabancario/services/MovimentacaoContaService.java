package com.projetofinal.sistemabancario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.sistemabancario.repositories.MovimentacaoContaRepository;

@Service
public class MovimentacaoContaService {
    
    @Autowired
    private MovimentacaoContaRepository movimentacaoContaRepository;

    @Autowired
    private ContaService contaService;
    

}
