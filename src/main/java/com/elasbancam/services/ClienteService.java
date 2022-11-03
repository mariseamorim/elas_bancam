package com.elasbancam.services;

import com.elasbancam.models.Cliente;
import com.elasbancam.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository _repository;


    public ClienteService(ClienteRepository repository) {
        _repository = repository;
    }

    @Transactional
    public Cliente save(Cliente cliente) {
        return _repository.save(cliente);
    }

    public List<Cliente> getAll(){
        return _repository.findAll();
    }

    public Optional<Cliente> getId(int idCliente){
       return  _repository.findById(idCliente);

    }

    public Cliente update(Cliente cliente) {
        return _repository.save(cliente);
    }

    public void deletar(int id) {
        _repository.deleteById(id);
    }


}
