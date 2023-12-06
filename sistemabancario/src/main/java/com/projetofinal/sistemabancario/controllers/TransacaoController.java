package com.projetofinal.sistemabancario.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.sistemabancario.domain.Transacao.TransacaoConta;
import com.projetofinal.sistemabancario.dtos.TransacaoDTO;
import com.projetofinal.sistemabancario.services.ContaService;
import com.projetofinal.sistemabancario.services.TransacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;


    @PostMapping
    public ResponseEntity<TransacaoConta> criarTransacao(@RequestBody @Valid TransacaoDTO data) throws Exception {
        
        TransacaoConta novaTransacao = transacaoService.criarTransacao(data);

        BeanUtils.copyProperties(data, novaTransacao);

        return new ResponseEntity<>(novaTransacao, HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<TransacaoConta>> getAllTransferencias() {
        List<TransacaoConta> listaTransferencias = this.transacaoService.getAllTransferencias(); 
        return new ResponseEntity<>(listaTransferencias, HttpStatus.OK);
    }

    //Método get para pegar as transações feitas por uma conta, enviadas ou recebidas (extrato)



}
