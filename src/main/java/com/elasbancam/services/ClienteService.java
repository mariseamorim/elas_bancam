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
        PessoaFisica resposta = new PessoaFisica();
        try {
            resposta = _repositoryPessoaFisica.save(pessoaFisica);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    @Transactional
    public PessoaJuridica savePj(PessoaJuridica pessoaJuridica) {
        PessoaJuridica resposta = new PessoaJuridica();
        try {
            resposta = _repositoryPessoaJuridica.save(pessoaJuridica);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    public List<Object> getAll() {
        List<Object> object = new ArrayList<Object>();
        try {
            var pf = _repositoryPessoaFisica.findAll();
            var pj = _repositoryPessoaJuridica.findAll();
            object.add(pf);
            object.add(pj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return object;
    }

    public Optional<PessoaFisica> getIdPf(Long idCliente) {
        Optional<PessoaFisica> resposta = Optional.of(new PessoaFisica());
        try {
            resposta = _repositoryPessoaFisica.findById(idCliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    public Optional<PessoaJuridica> getIdPj(Long idCliente) {
        Optional<PessoaJuridica> resposta = Optional.of(new PessoaJuridica());
        try {
            resposta = _repositoryPessoaJuridica.findById(idCliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    public Object getIdPjOrPf(Long idCliente) {
        Object object = new Object();
        try {
            Optional<PessoaFisica> pessoaFisica = getIdPf(idCliente);
            Optional<PessoaJuridica> pessoaJuridica = getIdPj(idCliente);
            if (pessoaFisica.isPresent()) {
                object = pessoaFisica.get();
            }
            if (pessoaJuridica.isPresent()) {
                object = pessoaJuridica.get();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return object;
    }

    public PessoaFisica updatePf(PessoaFisica cliente) {
        PessoaFisica resposta = new PessoaFisica();
        try {
            resposta = _repositoryPessoaFisica.save(cliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    public Pessoa updatePj(PessoaJuridica cliente) {
        PessoaJuridica resposta = new PessoaJuridica();
        try {
            resposta = _repositoryPessoaJuridica.save(cliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    @Transactional
    public void inactivePf(PessoaFisica pessoaFisica) {
        try {
            pessoaFisica.setStatus(false);
            pessoaFisica.setAlterado_em(LocalDateTime.now());
            savePf(pessoaFisica);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void inactivePJ(PessoaJuridica pessoaJuridica) {
        try {
            pessoaJuridica.setStatus(false);
            pessoaJuridica.setAlterado_em(LocalDateTime.now());
            savePj(pessoaJuridica);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
