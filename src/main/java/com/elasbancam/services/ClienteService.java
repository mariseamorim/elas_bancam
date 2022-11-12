package com.elasbancam.services;

import com.elasbancam.models.PessoaFisica;
import com.elasbancam.models.PessoaJuridica;
import com.elasbancam.repositories.PessoaFisicaRepository;
import com.elasbancam.repositories.PessoaJuridicaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




@AllArgsConstructor
@Component
@Service
public class ClienteService {

    private PessoaFisicaRepository _repositoryPessoaFisica;

    private PessoaJuridicaRepository _repositoryPessoaJuridica;

    @Transactional
    public PessoaFisica save(PessoaFisica pessoaFisica) {
        return _repositoryPessoaFisica.save(pessoaFisica);
    }

    @Transactional
    public PessoaJuridica save(PessoaJuridica pessoaJuridica) {
        return _repositoryPessoaJuridica.save(pessoaJuridica);
    }

    public List<Object> getAll(){
        List<Object> object = new ArrayList<Object>();
        var pf = _repositoryPessoaFisica.findAll();
        var pj = _repositoryPessoaJuridica.findAll();
        object.add(pf);
        object.add(pj);
        return object;
    }

    public Optional<PessoaFisica> getId(Long idCliente){
       return  _repositoryPessoaFisica.findById(idCliente);

    }

    public PessoaFisica update(PessoaFisica cliente) {
        return _repositoryPessoaFisica.save(cliente);
    }

    @Transactional
    public void deletar(Long id) {
        _repositoryPessoaFisica.deleteById(id);
    }


}
