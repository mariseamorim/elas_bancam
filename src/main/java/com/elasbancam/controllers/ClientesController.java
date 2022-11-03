package com.elasbancam.controllers;

import com.elasbancam.models.Cliente;
import com.elasbancam.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClientesController {
    @Autowired
    private ClienteService service;

    @PostMapping
    public void post(@RequestBody Cliente cliente){
        service.save(cliente);
    }

    @GetMapping("/todos")
    public List<Cliente> getAll(){
        return  service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> getId(@PathVariable int id){
        return  service.getId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody Cliente cliente, @PathVariable int id ){
        var cli = service.getId(id);
        if(!cli.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");;

        return ResponseEntity.status(HttpStatus.OK).body(service.update(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id ){
        var cli = service.getId(id);
        if(!cli.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        service.deletar(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
