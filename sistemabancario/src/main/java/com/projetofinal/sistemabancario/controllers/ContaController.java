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

import com.projetofinal.sistemabancario.domain.conta.Conta;
import com.projetofinal.sistemabancario.dtos.ContaDTO;
import com.projetofinal.sistemabancario.services.ClienteService;
import com.projetofinal.sistemabancario.services.ContaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContaController {
    

    @Autowired
    private ContaService contaService;


    //Métodos abaixo para a criação de uma conta após o cadastro de um cliente 

    @PostMapping
    public ResponseEntity<Conta> criarConta(@RequestBody @Valid ContaDTO contaDto) throws Exception{
         
        Conta novaConta = this.contaService.criarConta(contaDto);

        return new ResponseEntity<>(novaConta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Conta>> getAllContas(){
        List<Conta> listaAllContas = this.contaService.getAllContas();
        return new ResponseEntity<>(listaAllContas, HttpStatus.OK);
    }



}
