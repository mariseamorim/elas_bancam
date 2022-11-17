package com.elasbancam.services;

import com.elasbancam.exceptions.*;
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
            if (_repositoryPessoaFisica.findByCPF(pessoaFisica.getCpf()).isPresent()) {
                throw new CPFJaCadastradoException();
            } else if (_repositoryPessoaFisica.findByRG(pessoaFisica.getRg()).isPresent()) {
                throw new RGJaCadastradoException();
            } else {
                resposta = _repositoryPessoaFisica.save(pessoaFisica);
            }
        } catch (CPFJaCadastradoException | RGJaCadastradoException e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    @Transactional
    public PessoaJuridica savePj(PessoaJuridica pessoaJuridica) {
        PessoaJuridica resposta = new PessoaJuridica();
        try {
            if (_repositoryPessoaJuridica.findByCNPJ(pessoaJuridica.getCnpj()).isPresent()) {
                throw new CNPJJaCadastradoException();
            } else if (_repositoryPessoaJuridica.findByInscricao(pessoaJuridica.getInscricao_estadual()).isPresent()) {
                throw new InscricaoJaCadastradaExeption();
            } else {
                resposta = _repositoryPessoaJuridica.save(pessoaJuridica);
            }
        } catch (CNPJJaCadastradoException | InscricaoJaCadastradaExeption e) {
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
            if (resposta.isEmpty()){
                throw new IDNaoExistenteException();
            }
        } catch (IDNaoExistenteException e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    public Optional<PessoaJuridica> getIdPj(Long idCliente) {
        Optional<PessoaJuridica> resposta = Optional.of(new PessoaJuridica());
        try {
            resposta = _repositoryPessoaJuridica.findById(idCliente);
            if (resposta.isEmpty()){
                throw new IDNaoExistenteException();
            }
        } catch (IDNaoExistenteException e) {
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
            if (getIdPf(cliente.getId()).isEmpty()){
                throw new IDNaoExistenteException();
            }
            resposta = _repositoryPessoaFisica.save(cliente);
        } catch (IDNaoExistenteException e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    public Pessoa updatePj(PessoaJuridica cliente) {
        PessoaJuridica resposta = new PessoaJuridica();
        try {
            if (getIdPj(cliente.getId()).isEmpty()){
                throw new IDNaoExistenteException();
            }
            resposta = _repositoryPessoaJuridica.save(cliente);
        } catch (IDNaoExistenteException e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    @Transactional
    public void inactivePf(PessoaFisica pessoaFisica) {
        try {
            if (getIdPf(pessoaFisica.getId()).isEmpty()){
                throw new IDNaoExistenteException();
            }
            pessoaFisica.setStatus(false);
            pessoaFisica.setAlterado_em(LocalDateTime.now());
            savePf(pessoaFisica);
        } catch (IDNaoExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inactivePJ(PessoaJuridica pessoaJuridica) {
        try {
            if (getIdPj(pessoaJuridica.getId()).isEmpty()){
                throw new IDNaoExistenteException();
            }
            pessoaJuridica.setStatus(false);
            pessoaJuridica.setAlterado_em(LocalDateTime.now());
            savePj(pessoaJuridica);
        } catch (IDNaoExistenteException e) {
            System.out.println(e.getMessage());
        }
    }
}
