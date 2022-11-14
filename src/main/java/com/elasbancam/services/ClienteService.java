package com.elasbancam.services;

import com.elasbancam.models.Pessoa;
import com.elasbancam.models.PessoaFisica;
import com.elasbancam.models.PessoaJuridica;
import com.elasbancam.repositories.PessoaFisicaRepository;
import com.elasbancam.repositories.PessoaJuridicaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
    public PessoaFisica savePf(PessoaFisica pessoaFisica) {
        
        return _repositoryPessoaFisica.save(pessoaFisica);
    }

    @Transactional
    public PessoaJuridica savePj(PessoaJuridica pessoaJuridica) {
        return _repositoryPessoaJuridica.save(pessoaJuridica);
    }

    public List<Object> getAll() {
        List<Object> object = new ArrayList<Object>();
        var pf = _repositoryPessoaFisica.findAll();
        var pj = _repositoryPessoaJuridica.findAll();
        object.add(pf);
        object.add(pj);
        return object;
    }

    public Optional<PessoaFisica> getIdPf(Long idCliente) {
        return _repositoryPessoaFisica.findById(idCliente);

    }

    public Optional<PessoaJuridica> getIdPj(Long idCliente) {
        return _repositoryPessoaJuridica.findById(idCliente);

    }

    public Object getIdPjOrPf(Long idCliente) {
        var pessoaFisica = getIdPf(idCliente);
        var pessoaJuridica = getIdPj(idCliente);
        if (pessoaFisica.isPresent()) {
            return pessoaFisica.get();
        }
        if (pessoaJuridica.isPresent()) {
            return pessoaJuridica.get();
        }
        return null;
    }

    public PessoaFisica updatePf(PessoaFisica cliente) {
        return _repositoryPessoaFisica.save(cliente);
    }

    public Pessoa updatePj(PessoaJuridica cliente) {
        return _repositoryPessoaJuridica.save(cliente);
    }


    @Transactional
    public void inactivePf(PessoaFisica pessoaFisica) {
        pessoaFisica.setStatus(false);
        pessoaFisica.setAlterado_em(LocalDateTime.now());
        savePf(pessoaFisica);
    }

    public void inactivePJ(PessoaJuridica pessoaJuridica) {
        pessoaJuridica.setStatus(false);
        pessoaJuridica.setAlterado_em(LocalDateTime.now());
        savePj(pessoaJuridica);

    }


}
