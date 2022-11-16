package com.elasbancam.controllers;

import com.elasbancam.exceptions.IDNaoExistenteException;
import com.elasbancam.models.PessoaFisica;
import com.elasbancam.models.PessoaJuridica;
import com.elasbancam.services.ClienteService;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.joda.time.Days;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClientesController {

    private ClienteService service;

    @PostMapping("/pf")
    public ResponseEntity<Object> post(@RequestBody @Valid PessoaFisica pessoaFisica){
        LocalDate dt_nascimento = pessoaFisica.getDt_nascimento();
        Date date = new Date(System.currentTimeMillis());
        int days = Days.daysBetween(new DateTime(dt_nascimento), new DateTime(date)).getDays();
        if (days > 17)
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pessoaFisica));
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/pj")
    public  ResponseEntity<Object> post (@RequestBody @Valid PessoaJuridica pessoaJuridica){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pessoaJuridica));
    }

    @GetMapping("/todos")
    public List<Object> getAll(){
        return  service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<PessoaFisica> getId(@PathVariable Long id) throws IDNaoExistenteException {
        return  service.getId(id);
    }

    @PutMapping("/{id}")
    public Object update(@RequestBody PessoaFisica cliente, @PathVariable Long id) throws IDNaoExistenteException {
        var cli = service.getId(id);
        if (!cli.isPresent())
            return service.getId(id);
        return ResponseEntity.status(HttpStatus.OK).body(service.update(cliente));
    }
}


//
//    //Mudar para PUT (/delete/{id})
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id ){
//        var cli = service.getId(id);
//        if(!cli.isPresent())
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//
//        service.deletar(id);
//
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
//
//}
