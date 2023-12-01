package com.projetofinal.sistemabancario.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getContaById(@PathVariable UUID id){
        Conta conta = this.contaService.getContaById(id);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizaConta(@PathVariable UUID id, @RequestBody ContaDTO contaDto) throws Exception{
        Conta conta = this.contaService.atualizaConta(id, contaDto);


        return new ResponseEntity<>(conta, HttpStatus.OK);

    }


    @DeleteMapping("{id}")
    public ResponseEntity<Conta> deletaConta(@PathVariable UUID id) throws Exception{
        Conta conta = this.contaService.deletaConta(id);

        return new ResponseEntity<>(conta, HttpStatus.OK);

    }



}
