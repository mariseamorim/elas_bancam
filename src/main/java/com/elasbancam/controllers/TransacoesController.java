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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    private TransacoesService service;

    @PostMapping
    public ResponseEntity<Transacao> post(@RequestBody Transacao transacao){
        System.out.println(transacao.getTipo_transacao());
        Calendar now = new GregorianCalendar();
        int nowHour = now.get(Calendar.HOUR);
        if (nowHour > 8 && nowHour < 19)
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(transacao));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/tipo/{tipoTransacao}")
    public List<Transacao> getByType(@PathVariable TipoTransacao tipoTransacao){
        return ResponseEntity.status(HttpStatus.OK).body(service.getByType(tipoTransacao)).getBody();
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
    public List<Transacao> getByAccount(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getByAccount(id)).getBody();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Transacao> getById(@PathVariable String id){
        Object resposta = new Object();
        try{
            resposta = ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
        } catch (Exception e) {

        }

        return (ResponseEntity<Transacao>) resposta;
    }

}
