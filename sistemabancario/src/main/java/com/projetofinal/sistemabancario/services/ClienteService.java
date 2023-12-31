package com.projetofinal.sistemabancario.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.sistemabancario.domain.cliente.Cliente;
import com.projetofinal.sistemabancario.dtos.ClienteDTO;
import com.projetofinal.sistemabancario.repositories.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    //Método para criar buscar um cliente pelo cpf ou cnpj
    public Cliente findClienteByCpfCnpj(String cpfCnpj) {
        return this.clienteRepository.findByCpfCnpj(cpfCnpj);
    }


    //Método para criação de cliente
    public Cliente criarCliente(ClienteDTO data){
        Cliente novoCliente = new Cliente(data);

        this.salvarCliente(novoCliente);
        return novoCliente;
    }

    //Método para listar os clientes
    public List<Cliente> getAllClientes() {
        return this.clienteRepository.findAll();
    }

    //Metodo para buscar um cliente pelo id
    public Cliente findClienteById(UUID id) {
        return this.clienteRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Cliente não encontrado"));
    }




    //Método para atualizar cliente 
    public Cliente atualizarCliente(UUID id, ClienteDTO clienteDTO) {

        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Cliente não encontrado"));

        // Atualizar os dados do cliente com base no DTO recebido
        if (clienteDTO.nome() != null) {
            clienteExistente.setNome(clienteDTO.nome());
        }
    
        if (clienteDTO.sobrenome() != null) {
            clienteExistente.setSobrenome(clienteDTO.sobrenome());
        }

        if (clienteDTO.cpfCnpj() != null) {
            clienteExistente.setCpfCnpj(clienteDTO.cpfCnpj());
        }

        if (clienteDTO.email() != null) {
            clienteExistente.setEmail(clienteDTO.email());
        }

        if (clienteDTO.senha() != null) {
            clienteExistente.setSenha(clienteDTO.senha());
        }

        if (clienteDTO.tipoCliente() != null) {
            clienteExistente.setTipoCliente(clienteDTO.tipoCliente());
        }

        //Salvando a atualização
        return clienteRepository.save(clienteExistente);
    }

    //Método parta deletar um cliente
    public Cliente deletarCliente(UUID id) {
        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Cliente não encontrado"));

        clienteRepository.delete(clienteExistente);

        return clienteExistente;

    }

    //Método para buscar o extrato de um determinado cliente



    //Método para salvar o cliente no banco de dados
    public void salvarCliente(Cliente cliente){
        this.clienteRepository.save(cliente);
    }


}
