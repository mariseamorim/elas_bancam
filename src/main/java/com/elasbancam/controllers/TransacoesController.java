package com.elasbancam.controllers;

import com.elasbancam.dtos.input.TransacaoDto;
import com.elasbancam.exceptions.NegocioException;
import com.elasbancam.models.Conta;
import com.elasbancam.models.Transacao;
import com.elasbancam.enums.TipoTransacao;
import com.elasbancam.services.ContaService;
import com.elasbancam.services.TransacoesService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    private TransacoesService service;

    private ContaService contaService;

    @PostMapping
    public ResponseEntity<Transacao> create(@RequestBody @Valid TransacaoDto transacaoDto){
        Transacao novaTransacao = new Transacao();
        try {
            Conta novaContaOrigem = new Conta();
            Conta novaContaDestino = new Conta();

            novaContaOrigem.setId(transacaoDto.getConta_origem().getId());
            novaContaDestino.setId(transacaoDto.getConta_destino().getId());

            novaTransacao.setConta_origem(novaContaOrigem);
            novaTransacao.setConta_destino(novaContaDestino);
            novaTransacao.setTipo_transacao(transacaoDto.getTipo_transacao());
            novaTransacao.setValor(transacaoDto.getValor());
            novaTransacao.setDescricao(transacaoDto.getDescricao());
        } catch (NegocioException e) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(novaTransacao));
    }

    @GetMapping("/tipo/{tipoTransacao}")
    public ResponseEntity<List<Transacao>> getByType(@PathVariable TipoTransacao tipoTransacao){
        ResponseEntity<List<Transacao>> resposta = null;
        try {
            resposta = ResponseEntity.status(HttpStatus.OK).body(service.getByType(tipoTransacao));
        } catch (NegocioException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return resposta;
    }

    @GetMapping("/periodo/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Transacao>> getByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal) {
        ResponseEntity<List<Transacao>> resposta = null;
        try {
            resposta = ResponseEntity.status(HttpStatus.OK).body(service.getByDate(dataInicial, dataFinal));
        } catch (NegocioException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return resposta;
    }

    @GetMapping("/conta/{id}")
    public ResponseEntity<Object> getByAccount(@PathVariable String id){
        ResponseEntity<Object> resposta = null;
        try {
            Object conta= contaService.getById(id);
            if(ObjectUtils.isEmpty(conta)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
            }
            resposta = ResponseEntity.status(HttpStatus.OK).body(service.getByAccount(id));
        } catch (NegocioException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return resposta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id){
        ResponseEntity<Object> resposta = null;
        try {
            Object transacao = service.getById(id);
            if(ObjectUtils.isEmpty(transacao)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transacao não encontrada.");
            }
            resposta = ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
        } catch (NegocioException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return resposta;
    }

}
