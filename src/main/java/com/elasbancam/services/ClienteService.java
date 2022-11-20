package com.elasbancam.services;

import com.elasbancam.controllers.mappers.ClienteMapper;
import com.elasbancam.dtos.PessoaFisicaUpdateDto;
import com.elasbancam.dtos.PessoaJuridicaUpdateDto;
import com.elasbancam.exceptions.NegocioException;
import com.elasbancam.models.Conta;
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

// Os métodos das camadas de service fazem validações de regras de negócio e lançam erros para que os
// controllers os tratem.

@AllArgsConstructor
@Component
@Service
public class ClienteService {

    private PessoaFisicaRepository _repositoryPessoaFisica;

    private PessoaJuridicaRepository _repositoryPessoaJuridica;

    private ContaService contaService;

    private ClienteMapper clienteMapper;

    @Transactional
    public PessoaFisica cadastrarPessoaFisica(PessoaFisica pessoaFisica) {
        Optional<PessoaFisica> cpfJaCadastrado = _repositoryPessoaFisica.buscarPfPorCPF(pessoaFisica.getCpf());
        if (cpfJaCadastrado.isPresent()) {
            throw new NegocioException("CPF já cadastrado no sistema.");
        }

        Optional<PessoaFisica> rgJaCadastrado = _repositoryPessoaFisica.buscarPfPorRG(pessoaFisica.getRg());
        if (rgJaCadastrado.isPresent()) {
            throw new NegocioException("RG já cadastrado no sistema.");
        }

        Conta numeroContaJaCadastrado = contaService.buscarContaPorNumeroConta(pessoaFisica.getConta().getNumero_conta());
        if (numeroContaJaCadastrado != null) {
            throw new NegocioException("Número de conta já cadastrado no sistema e não pode ser usado.");
        }

        if (pessoaFisica.getConta().getSaldo().compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new NegocioException("Saldo inicial deve ser maior ou igual a zero.");
        }

        try {
            return _repositoryPessoaFisica.save(pessoaFisica);
        } catch (NegocioException e) {
            throw new NegocioException("Não foi possível concluir o cadastro.");
        }
    }

    @Transactional
    public PessoaJuridica cadastrarPessoaJuridica(PessoaJuridica pessoaJuridica) {
        Optional<PessoaJuridica> cnpjJaCadastrado = _repositoryPessoaJuridica.buscarPjPorCNPJ(pessoaJuridica.getCnpj());
        if (cnpjJaCadastrado.isPresent()) {
            throw new NegocioException("CNPJ já cadastrado no sistema.");
        }

        Optional<PessoaJuridica> inscricaoEstadualJaCadastrada = _repositoryPessoaJuridica.buscarPjPorInscricao(pessoaJuridica.getInscricao_estadual());
        if (inscricaoEstadualJaCadastrada.isPresent()) {
            throw new NegocioException("Inscrição Estadual já cadastrada no sistema.");
        }

        Conta numeroContaJaCadastrado = contaService.buscarContaPorNumeroConta(pessoaJuridica.getConta().getNumero_conta());
        if (numeroContaJaCadastrado != null) {
            throw new NegocioException("Número de conta já cadastrado no sistema e não pode ser usado.");
        }

        if (pessoaJuridica.getConta().getSaldo().compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new NegocioException("Saldo inicial deve ser maior ou igual a zero.");
        }

        try {
            return _repositoryPessoaJuridica.save(pessoaJuridica);
        } catch (NegocioException e) {
            throw new NegocioException("Não foi possível concluir o cadastro.");
        }
    }

    public List<Object> listarTodosClientes() {
        List<Object> listaDeClientes = new ArrayList<>();
        List<PessoaFisica> pf = _repositoryPessoaFisica.listarTodosPF();
        List<PessoaJuridica> pj = _repositoryPessoaJuridica.listarTodosPJ();
        listaDeClientes.add(pf);
        listaDeClientes.add(pj);
        return listaDeClientes;
    }


    public Optional<PessoaFisica> listarPessoaFisicaPorId(Long idCliente) {
        return _repositoryPessoaFisica.buscarPfPorId(idCliente);
    }

    public Optional<PessoaJuridica> listarPessoaJuridicaPorId(Long idCliente) {
        return _repositoryPessoaJuridica.buscarPjPorId(idCliente);
    }

    public boolean verificarSeClienteExiste(Long id){
        Optional<PessoaFisica> pf = listarPessoaFisicaPorId(id);
        Optional<PessoaJuridica> pj = listarPessoaJuridicaPorId(id);

        if(pf.isPresent() || pj.isPresent()){
            return true;
        }
        return false;
    }

    public Object listarClientePorId(Long idCliente) {
        Object cliente = new Object();

        Optional<PessoaFisica> pessoaFisica = listarPessoaFisicaPorId(idCliente);
        Optional<PessoaJuridica> pessoaJuridica = listarPessoaJuridicaPorId(idCliente);

        if (pessoaFisica.isEmpty() && pessoaJuridica.isEmpty()) {
            throw new NegocioException("Cliente não encontrado ou inativo (ID:  " + idCliente + ").");
        }

        if (pessoaFisica.isPresent()) {
            cliente = pessoaFisica.get();
        } else {
            cliente = pessoaJuridica.get();
        }

        return cliente;
    }

    public PessoaFisica atualizarPessoaFisica(PessoaFisicaUpdateDto pessoa) {
        Optional<PessoaFisica> clienteExiste = listarPessoaFisicaPorId(pessoa.getId());
        if (clienteExiste.isEmpty()) {
            throw new NegocioException("Cliente não encontrado ou inativo (ID:  " + pessoa.getId() + ").");
        }
        clienteMapper.toEntityUpdatePf(pessoa, clienteExiste.get());

        try {
            return _repositoryPessoaFisica.save(clienteExiste.get());
        } catch (NegocioException e) {
            throw new NegocioException("Não foi possível concluir a atualização do cliente.");
        }
    }


    public PessoaJuridica atualizarPessoaJuridica(PessoaJuridicaUpdateDto pessoa) {
        Optional<PessoaJuridica> clienteExiste = listarPessoaJuridicaPorId(pessoa.getId());
        if (clienteExiste.isEmpty()) {
            throw new NegocioException("Cliente não encontrado ou inativo (ID:  " + pessoa.getId() + ").");
        }
        clienteMapper.toEntityUpdatePj(pessoa, clienteExiste.get());

        try {
            return _repositoryPessoaJuridica.save(clienteExiste.get());
        } catch (NegocioException e) {
            throw new NegocioException("Não foi possível concluir a atualização do cliente.");
        }
    }


    @Transactional
    public PessoaFisica inativarPessoaFisica(PessoaFisica pessoaFisica) {
        try {
            pessoaFisica.setStatus(false);
            pessoaFisica.setAlterado_em(LocalDateTime.now());
            contaService.inativarConta(pessoaFisica.getConta().getId());
            return _repositoryPessoaFisica.save(pessoaFisica);
        } catch (NegocioException e) {
            throw new NegocioException("Não foi possível inativar o cliente (ID: " + pessoaFisica.getId() + ").");
        }
    }

    public PessoaJuridica inativarPessoaJuridica(PessoaJuridica pessoaJuridica) {
        try {
            pessoaJuridica.setStatus(false);
            pessoaJuridica.setAlterado_em(LocalDateTime.now());
            contaService.inativarConta(pessoaJuridica.getConta().getId());
            return  _repositoryPessoaJuridica.save(pessoaJuridica);
        } catch (NegocioException e) {
            throw new NegocioException("Não foi possível inativar o cliente (ID: " + pessoaJuridica.getId() + ").");
        }
    }
}
