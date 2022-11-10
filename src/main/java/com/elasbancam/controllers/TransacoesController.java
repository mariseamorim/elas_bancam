package com.elasbancam.controllers;

import com.elasbancam.dtos.DataTransacaoDto;
import com.elasbancam.models.Transacao;
import com.elasbancam.enums.TipoTransacao;
import com.elasbancam.services.TransacoesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    private TransacoesService service;

    @PostMapping
    public ResponseEntity<Transacao> post(@RequestBody Transacao transacao){
        Object resposta = new Object();
        try{
            resposta = ResponseEntity.status(HttpStatus.CREATED).body(service.save(transacao));
        } catch (Exception e) {

        }

        return (ResponseEntity<Transacao>) resposta;
    }

    @GetMapping("/tipo")
    public List<Transacao> getByType(@RequestParam TipoTransacao tipoTransacao){
        Object resposta = new Object();
        try{
            resposta = ResponseEntity.status(HttpStatus.OK).body(service.getByType(tipoTransacao));
        } catch (Exception e) {

        }

        return (List<Transacao>) resposta;
    }

    @GetMapping("/periodo")
    public List<Transacao> getByDate(@RequestBody @Valid DataTransacaoDto periodo){
        Object resposta = new Object();
        try{
            //Fazer mapeamento da DTO pra entidade
            resposta = ResponseEntity.status(HttpStatus.OK).body(service.getByDate(periodo.getDataInicial(), periodo.getDataFinal()));
        } catch (Exception e) {

        }

        return (List<Transacao>) resposta;
    }

    @GetMapping("/conta/{id}")
    public List<Transacao> getByAccount(@PathVariable UUID id){
        Object resposta = new Object();
        // Fazer validação de dados (id é válido?)
        try{
            resposta = ResponseEntity.status(HttpStatus.OK).body(service.getByAccount(id));
        } catch (Exception e) {

        }

        return (List<Transacao>) resposta;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Transacao> getById(@PathVariable UUID id){
        Object resposta = new Object();
        try{
            resposta = ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
        } catch (Exception e) {

        }

        return (ResponseEntity<Transacao>) resposta;
    }

}
