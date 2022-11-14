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
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        var clientes = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(clientes);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable Long id){
        Object cliente= service.getIdPjOrPf(id);
        if(ObjectUtils.isEmpty(cliente)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PutMapping("/pf")
    public ResponseEntity<Object> updatePf(@RequestBody PessoaFisicaUpdateDto pessoa){
        var cliente = service.getIdPf(pessoa.getId());
        if(!cliente.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        clienteMapper.toEntityUpdatePf(pessoa, cliente.get());
       return ResponseEntity.status(HttpStatus.OK).body(service.updatePf(cliente.get()));
    }
    @PutMapping("/pj")
    public ResponseEntity<Object> updatePj(@RequestBody PessoaJuridicaUpdateDto pessoa ){
        var cliente = service.getIdPj(pessoa.getId());
        if(!cliente.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        clienteMapper.toEntityUpdatePj(pessoa, cliente.get());
        return ResponseEntity.status(HttpStatus.OK).body(service.updatePj(cliente.get()));
    }


    @PutMapping("delete/{id}")
    public ResponseEntity<Void> inactive(@PathVariable Long id ){
        var clientePf = service.getIdPf(id);
        var clientePj = service.getIdPj(id);

        if(!clientePf.isPresent() && !clientePj.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if(clientePf.isPresent()){
            service.inactivePf(clientePf.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        service.inactivePJ(clientePj.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
