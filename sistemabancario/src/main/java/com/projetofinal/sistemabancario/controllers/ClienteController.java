package com.projetofinal.sistemabancario.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.sistemabancario.domain.cliente.Cliente;
import com.projetofinal.sistemabancario.dtos.ClienteDTO;
import com.projetofinal.sistemabancario.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

     //O CRUD abaixo será responsável pelo cadastro do cliente 

    @CrossOrigin(origins = "*", allowedHeaders = "*") 
    @PostMapping
    public ResponseEntity<Cliente> criaCliente(@RequestBody @Valid ClienteDTO cliente){
        Cliente novoCliente = clienteService.criarCliente(cliente);

        BeanUtils.copyProperties(cliente, novoCliente);

        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*") 
    //origins: Define quais origens (domínios) são permitidas acessar a API.
    //allowedHeaders: Define quais cabeçalhos HTTP podem ser usados durante a solicitação real.
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes(){
        List<Cliente> clientes = this.clienteService.getAllClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteByID(@PathVariable UUID id) throws Exception{

        Cliente cliente = this.clienteService.findClienteById(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizaCliente(@PathVariable UUID id, @RequestBody @Valid ClienteDTO clienteDTO){
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, clienteDTO);

        BeanUtils.copyProperties(clienteDTO, clienteAtualizado);

        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletaCliente(@PathVariable UUID id){
        try {
            Cliente clienteDeletado = clienteService.deletarCliente(id);
            return new ResponseEntity<>(clienteDeletado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
