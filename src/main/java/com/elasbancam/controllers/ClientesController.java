package com.elasbancam.controllers;

import com.elasbancam.dtos.input.PessoaFisicaDto;
import com.elasbancam.dtos.input.PessoaFisicaUpdateDto;
import com.elasbancam.dtos.input.PessoaJuridicaDto;
import com.elasbancam.controllers.mappers.ClienteMapper;
import com.elasbancam.dtos.input.PessoaJuridicaUpdateDto;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePf(novoCliente));
    }

   @PostMapping("/pj")
    public  ResponseEntity<Object> post (@RequestBody @Valid PessoaJuridicaDto pessoaJuridicaDto){
        PessoaJuridica novoCliente = clienteMapper.toEntityPj(pessoaJuridicaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePj(novoCliente));

    }

    @GetMapping("/todos")
    public ResponseEntity<List<Object>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getIdPjOrPf(id));
    }

    @PutMapping("/pf")
    public ResponseEntity<Object> updatePf(@RequestBody PessoaFisicaUpdateDto pessoa){
        return ResponseEntity.status(HttpStatus.OK).body(service.updatePf(pessoa));
    }
    @PutMapping("/pj")
    public ResponseEntity<Object> updatePj(@RequestBody PessoaJuridicaUpdateDto pessoa){
        return ResponseEntity.status(HttpStatus.OK).body(service.updatePj(pessoa));
    }

    @PutMapping("delete/{id}")
    public ResponseEntity<Void> inactive(@PathVariable Long id ){
        Optional<PessoaFisica> clientePf = service.getIdPf(id);
        Optional<PessoaJuridica> clientePj = service.getIdPj(id);

        if(clientePf.isPresent()){
            service.inactivePf(clientePf.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        service.inactivePJ(clientePj.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
