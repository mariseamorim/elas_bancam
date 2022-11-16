package com.elasbancam.controllers;

import com.elasbancam.dtos.input.DataTransacaoDto;
import com.elasbancam.dtos.input.TransacaoDto;
import com.elasbancam.models.Conta;
import com.elasbancam.models.Transacao;
import com.elasbancam.enums.TipoTransacao;
import com.elasbancam.services.ContaService;
import com.elasbancam.services.TransacoesService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    private TransacoesService service;

    private ContaService contaService;

//    private TransacaoMapper transacaoMapper;

    @PostMapping
    public ResponseEntity<Transacao> create(@RequestBody @Valid TransacaoDto transacaoDto){
        Transacao novaTransacao = new Transacao();
        Conta novaContaOrigem = new Conta();
        Conta novaContaDestino = new Conta();

        novaContaOrigem.setId(transacaoDto.getConta_origem().getId());
        novaContaDestino.setId(transacaoDto.getConta_destino().getId());

        novaTransacao.setConta_origem(novaContaOrigem);
        novaTransacao.setConta_destino(novaContaDestino);
        novaTransacao.setTipo_transacao(transacaoDto.getTipo_transacao());
        novaTransacao.setValor(transacaoDto.getValor());
        novaTransacao.setDescricao(transacaoDto.getDescricao());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(novaTransacao));
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
    public ResponseEntity<Object> getByAccount(@PathVariable String id){
        Object conta= contaService.getById(id);
        if(ObjectUtils.isEmpty(conta)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.getByAccount(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id){
        Object transacao = service.getById(id);
        if(ObjectUtils.isEmpty(transacao)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transacao não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

}
