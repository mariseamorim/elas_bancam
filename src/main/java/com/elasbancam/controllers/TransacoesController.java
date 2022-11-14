package com.elasbancam.controllers;

import com.elasbancam.dtos.input.DataTransacaoDto;
import com.elasbancam.models.Transacao;
import com.elasbancam.enums.TipoTransacao;
import com.elasbancam.services.TransacoesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    private TransacoesService service;

    @PostMapping
    public ResponseEntity<Transacao> create(@RequestBody Transacao transacao){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(transacao));
    }

    @GetMapping("/tipo/{tipoTransacao}")
    public ResponseEntity<List<Transacao>> getByType(@PathVariable TipoTransacao tipoTransacao){
        return ResponseEntity.status(HttpStatus.OK).body(service.getByType(tipoTransacao));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Transacao>> getByDate(@RequestBody @Valid DataTransacaoDto periodo) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByDate(periodo.getDataInicial(), periodo.getDataFinal()));
    }

    @GetMapping("/conta/{id}")
    public ResponseEntity<List<Transacao>> getByAccount(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getByAccount(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transacao>> getById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

}
