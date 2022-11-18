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

@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClientesController {
    private ClienteService service;

    private ClienteMapper clienteMapper;

    @PostMapping("/pf")
    public ResponseEntity<Object> post(@RequestBody @Valid  PessoaFisicaDto pessoaFisicaDto){
        PessoaFisica novoCliente = clienteMapper.toEntityPf(pessoaFisicaDto);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.savePf(novoCliente));
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

   @PostMapping("/pj")
    public  ResponseEntity<Object> post (@RequestBody @Valid PessoaJuridicaDto pessoaJuridicaDto){
        PessoaJuridica novoCliente = clienteMapper.toEntityPj(pessoaJuridicaDto);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.savePj(novoCliente));
        }catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Object>> getAll(){
        List<Object> resposta = null;
        try {
            resposta = service.getAll();
            if(resposta.isEmpty()){
                throw new NegocioException("Ainda não há clientes cadastrados.");
            }
        } catch (NegocioException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getIdPjOrPf(id));
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/pf")
    public ResponseEntity<Object> updatePf(@RequestBody PessoaFisicaUpdateDto pessoa){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.updatePf(pessoa));
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/pj")
    public ResponseEntity<Object> updatePj(@RequestBody PessoaJuridicaUpdateDto pessoa){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.updatePj(pessoa));
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("delete/{id}")
    public void inactive(@PathVariable Long id ){
        try {
            Optional<PessoaFisica> clientePf = service.getIdPf(id);
            Optional<PessoaJuridica> clientePj = service.getIdPj(id);

            if(clientePf.isPresent()){
                service.inactivePf(clientePf.get());
            } else if (clientePj.isPresent()){
                service.inactivePJ(clientePj.get());
            }
        } catch (NegocioException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
