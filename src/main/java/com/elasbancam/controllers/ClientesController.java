package com.elasbancam.controllers;

import com.elasbancam.dtos.input.PessoaFisicaDto;
import com.elasbancam.dtos.input.PessoaFisicaUpdateDto;
import com.elasbancam.dtos.input.PessoaJuridicaDto;
import com.elasbancam.controllers.mappers.ClienteMapper;
import com.elasbancam.dtos.input.PessoaJuridicaUpdateDto;
import com.elasbancam.exceptions.NegocioException;
import com.elasbancam.models.PessoaFisica;
import com.elasbancam.models.PessoaJuridica;
import com.elasbancam.services.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

// Foram definidas DTOs para receber os dados vindos da requisição.
// A inativação de cliente é feita através de soft delete, com a alteração do atributo "status" para false.
// Toda busca por clientes é feita filtrado apenas aqueles que estão ativo ("status" = verdadeiro) no sistema.

@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClientesController {
    private ClienteService service;

    private ClienteMapper clienteMapper;

    @PostMapping("/pf")
    public ResponseEntity post(@RequestBody @Valid  PessoaFisicaDto pessoaFisicaDto){
        PessoaFisica novoCliente = clienteMapper.toEntityPf(pessoaFisicaDto);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.savePf(novoCliente));
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

   @PostMapping("/pj")
    public  ResponseEntity post (@RequestBody @Valid PessoaJuridicaDto pessoaJuridicaDto){
        PessoaJuridica novoCliente = clienteMapper.toEntityPj(pessoaJuridicaDto);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.savePj(novoCliente));
        }catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity getAll(){
        try {
            List<Object> resposta = service.getAll();
            if(resposta.isEmpty()){
                throw new NegocioException("Ainda não há clientes cadastrados.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(resposta);
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getId(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getIdPjOrPf(id));
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/pf")
    public ResponseEntity updatePf(@RequestBody PessoaFisicaUpdateDto pessoa){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.updatePf(pessoa));
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/pj")
    public ResponseEntity updatePj(@RequestBody PessoaJuridicaUpdateDto pessoa){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.updatePj(pessoa));
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("delete/{id}")
    public ResponseEntity inactive(@PathVariable Long id ){
        try {
            Optional<PessoaFisica> clientePf = service.getIdPf(id);
            Optional<PessoaJuridica> clientePj = service.getIdPj(id);

            if(clientePf.isPresent()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.inactivePf(clientePf.get()));
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.inactivePJ(clientePj.get()));
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
