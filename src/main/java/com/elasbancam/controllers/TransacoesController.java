package com.elasbancam.controllers;

import com.elasbancam.controllers.mappers.TransacaoMapper;
import com.elasbancam.dtos.TransacaoDto;
import com.elasbancam.exceptions.NegocioException;
import com.elasbancam.enums.TipoTransacao;
import com.elasbancam.models.Transacao;
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

// Decidimos criar 3 buscas de transações para treinar o uso de diferentes tipos de variáveis:
// por tipo, período e conta.
// Foram definidas DTOs para receber os dados vindos da requisição.

@AllArgsConstructor
@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    private TransacoesService service;

    private ContaService contaService;
    private TransacaoMapper transacaoMapper;

    // Método escolhido para fazer o mapeamento de objetos manualmente
    // TO DO: refatorar o mapeamento
    @PostMapping
    public ResponseEntity efetuarTransacao(@RequestBody @Valid TransacaoDto transacaoDto){
        try {
            Transacao transacao = transacaoMapper.toEntityTransacao(transacaoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(service.efetuarTransacao(transacao));
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/tipo/{tipoTransacao}")
    public ResponseEntity listarTransacoesPorTipo(@PathVariable TipoTransacao tipoTransacao){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.listarTransacoesPorTipo(tipoTransacao));
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/periodo/{dataInicial}/{dataFinal}")
    public ResponseEntity listarTransacoesPorPeriodo(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.listarTransacoesPorPeriodo(dataInicial, dataFinal));
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/conta/{id}")
    public ResponseEntity listarTransacoesPorIdConta(@PathVariable String id){
        try {
            Object conta= contaService.buscarContaPorId(id);
            if(ObjectUtils.isEmpty(conta)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(service.listarTransacoesPorIdConta(id));
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarTransacaoPorId(@PathVariable String id){
        try {
            Object transacao = service.buscarTransacaoPorId(id);
            if(ObjectUtils.isEmpty(transacao)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transacao não encontrada.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(transacao);
        } catch (NegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
