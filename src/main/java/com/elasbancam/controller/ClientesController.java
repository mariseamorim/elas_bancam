package com.elasbancam.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ClientesController {

    @RequestMapping("/cliente")
    @GetMapping
    public String Funcao(){
        return "Hello World!";
    }
}
