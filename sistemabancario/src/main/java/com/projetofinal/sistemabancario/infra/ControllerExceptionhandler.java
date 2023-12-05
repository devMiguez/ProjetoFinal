package com.projetofinal.sistemabancario.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.projetofinal.sistemabancario.dtos.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;


@RestControllerAdvice
public class ControllerExceptionhandler {
    
    //Primeira: cadeastrom de um usuário com um documento que já existe
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity entradaDuplicadaDeDocumento(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDto = new ExceptionDTO("Este documento já pertence a um usuário cadastrado!!", "400");
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    //Segunda: quando não é encontrado uma entidade
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entidadeNaoEncontrada(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    //Terceira: Exceção geral 
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionGeral(Exception exception){
        ExceptionDTO exceptionDto = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDto);
    }

    



}
