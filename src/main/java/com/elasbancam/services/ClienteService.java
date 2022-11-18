package com.elasbancam.services;

import com.elasbancam.controllers.mappers.ClienteMapper;
import com.elasbancam.dtos.input.PessoaFisicaUpdateDto;
import com.elasbancam.dtos.input.PessoaJuridicaUpdateDto;
import com.elasbancam.exceptions.NegocioException;
import com.elasbancam.models.Pessoa;
import com.elasbancam.models.PessoaFisica;
import com.elasbancam.models.PessoaJuridica;
import com.elasbancam.repositories.PessoaFisicaRepository;
import com.elasbancam.repositories.PessoaJuridicaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
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

    private ClienteMapper clienteMapper;

    @Transactional
    public PessoaFisica savePf(PessoaFisica pessoaFisica) {
        Optional<PessoaFisica> cpfJaCadastrado = _repositoryPessoaFisica.findByCPF(pessoaFisica.getCpf());
        if (cpfJaCadastrado.isPresent()) {
            throw new NegocioException("CPF já cadastrado no sistema.");
        }

        Optional<PessoaFisica> rgJaCadastrado = _repositoryPessoaFisica.findByRG(pessoaFisica.getRg());
        if (rgJaCadastrado.isPresent()) {
            throw new NegocioException("RG já cadastrado no sistema.");
        }

        if (pessoaFisica.getConta().getSaldo().compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new NegocioException("Saldo inicial deve ser maior ou igual a zero.");
        }

        try {
            return _repositoryPessoaFisica.save(pessoaFisica);
        } catch (Exception e) {
            throw new NegocioException("Não foi possível concluir o cadastro.");
        }
    }

    @Transactional
    public PessoaJuridica savePj(PessoaJuridica pessoaJuridica) {
        Optional<PessoaJuridica> cnpjJaCadastrado = _repositoryPessoaJuridica.findByCNPJ(pessoaJuridica.getCnpj());
        if (cnpjJaCadastrado.isPresent()) {
            throw new NegocioException("CNPJ já cadastrado no sistema.");
        }

        Optional<PessoaJuridica> inscricaoEstadualJaCadastrada = _repositoryPessoaJuridica.findByInscricao(pessoaJuridica.getInscricao_estadual());
        if (inscricaoEstadualJaCadastrada.isPresent()) {
            throw new NegocioException("Inscrição Estadual já cadastrada no sistema.");
        }

        if (pessoaJuridica.getConta().getSaldo().compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new NegocioException("Saldo inicial deve ser maior ou igual a zero.");
        }

        try {
            return _repositoryPessoaJuridica.save(pessoaJuridica);
        } catch (Exception e) {
            throw new NegocioException("Não foi possível concluir o cadastro.");
        }
    }

    public List<Object> getAll() {
        List<Object> object = new ArrayList<Object>();
        List<PessoaFisica> pf = _repositoryPessoaFisica.findAll();
        List<PessoaJuridica> pj = _repositoryPessoaJuridica.findAll();
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
        Object object = new Object();

        Optional<PessoaFisica> pessoaFisica = getIdPf(idCliente);
        Optional<PessoaJuridica> pessoaJuridica = getIdPj(idCliente);

        if (pessoaFisica.isEmpty() && pessoaJuridica.isEmpty()) {
            throw new NegocioException("Cliente não encontrado ou inativo (ID:  " + idCliente + ").");
        }

        if (pessoaFisica.isPresent()) {
            object = pessoaFisica.get();
        } else {
            object = pessoaJuridica.get();
        }

        return object;
    }

    public PessoaFisica updatePf(PessoaFisicaUpdateDto pessoa) {
        Optional<PessoaFisica> clienteExiste = getIdPf(pessoa.getId());
        if (clienteExiste.isEmpty()) {
            throw new NegocioException("Cliente não encontrado ou inativo (ID:  " + pessoa.getId() + ").");
        }
        clienteMapper.toEntityUpdatePf(pessoa, clienteExiste.get());

        try {
            return _repositoryPessoaFisica.save(clienteExiste.get());
        } catch (Exception e) {
            throw new NegocioException("Não foi possível concluir a atualização do cliente.");
        }
    }


    public Pessoa updatePj(PessoaJuridicaUpdateDto pessoa) {

        Optional<PessoaJuridica> clienteExiste = getIdPj(pessoa.getId());
        if (clienteExiste.isEmpty()) {
            throw new NegocioException("Cliente não encontrado ou inativo (ID:  " + pessoa.getId() + ").");
        }
        clienteMapper.toEntityUpdatePj(pessoa, clienteExiste.get());

        try {
            return _repositoryPessoaJuridica.save(clienteExiste.get());
        } catch (Exception e) {
            throw new NegocioException("Não foi possível concluir a atualização do cliente.");
        }
    }


    @Transactional
    public PessoaFisica inactivePf(PessoaFisica pessoaFisica) {
        if (pessoaFisica == null) {
            throw new NegocioException("Cliente não encontrado ou já inativo.");
        }
        pessoaFisica.setStatus(false);
        pessoaFisica.setAlterado_em(LocalDateTime.now());

        try {
            return _repositoryPessoaFisica.save(pessoaFisica);
        } catch (Exception e) {
            throw new NegocioException("Não foi possível inativar o cliente (ID: " + pessoaFisica.getId() + ").");
        }
    }

    public PessoaJuridica inactivePJ(PessoaJuridica pessoaJuridica) {
        if (pessoaJuridica == null) {
            throw new NegocioException("Cliente não encontrado ou já inativo.");
        }
        pessoaJuridica.setStatus(false);
        pessoaJuridica.setAlterado_em(LocalDateTime.now());

        try {
            return  _repositoryPessoaJuridica.save(pessoaJuridica);
        } catch (Exception e) {
            throw new NegocioException("Não foi possível inativar o cliente (ID: " + pessoaJuridica.getId() + ").");
        }
    }
}
