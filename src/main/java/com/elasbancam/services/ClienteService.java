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
        try {
            Optional<PessoaFisica> cpfJaCadastrado = _repositoryPessoaFisica.findByCPF(pessoaFisica.getCpf());
            if(cpfJaCadastrado.isPresent()){
                throw new NegocioException("CPF já cadastrado no sistema.");
            }

            Optional<PessoaFisica> rgJaCadastrado = _repositoryPessoaFisica.findByRG(pessoaFisica.getRg());
            if(rgJaCadastrado.isPresent()){
                throw new NegocioException("RG já cadastrado no sistema.");
            }

            PessoaFisica pessoaCadastrada = _repositoryPessoaFisica.save(pessoaFisica);
            if(pessoaCadastrada == null) {
                throw new NegocioException("Não foi possível concluir o cadastro.");
            }
            return pessoaCadastrada;
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    @Transactional
    public PessoaJuridica savePj(PessoaJuridica pessoaJuridica) {
        try {
            Optional<PessoaJuridica> cnpjJaCadastrado = _repositoryPessoaJuridica.findByCNPJ(pessoaJuridica.getCnpj());
            if(cnpjJaCadastrado.isPresent()){
                throw new NegocioException("CNPJ já cadastrado no sistema.");
            }

            Optional<PessoaJuridica> inscricaoEstadualJaCadastrada = _repositoryPessoaJuridica.findByInscricao(pessoaJuridica.getInscricao_estadual());
            if(inscricaoEstadualJaCadastrada.isPresent()){
                throw new NegocioException("Inscrição Estadual já cadastrada no sistema.");
            }

            PessoaJuridica pessoaCadastrada = _repositoryPessoaJuridica.save(pessoaJuridica);
            if(pessoaCadastrada == null) {
                throw new NegocioException("Não foi possível concluir o cadastro.");
            }
            return pessoaCadastrada;
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public List<Object> getAll() {
        List<Object> object = new ArrayList<Object>();
        try {
            List<PessoaFisica> pf = _repositoryPessoaFisica.findAll();
            List<PessoaJuridica> pj = _repositoryPessoaJuridica.findAll();
            object.add(pf);
            object.add(pj);
            if(object.isEmpty()){
                throw new NegocioException("Ainda não há nenhum cliente cadastrado.");
            }
            return object;
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public Optional<PessoaFisica> getIdPf(Long idCliente) {
        try {
            return  _repositoryPessoaFisica.findById(idCliente);
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public Optional<PessoaJuridica> getIdPj(Long idCliente) {
        try {
            return _repositoryPessoaJuridica.findById(idCliente);
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public Object getIdPjOrPf(Long idCliente) {
        Object object = new Object();
        try {
            Optional<PessoaFisica> pessoaFisica = getIdPf(idCliente);
            Optional<PessoaJuridica> pessoaJuridica = getIdPj(idCliente);

            if(pessoaFisica.isEmpty() && pessoaJuridica.isEmpty()){
                throw new NegocioException("Cliente não encontrado ou inativo (ID:  " + idCliente + ").");
            }

            if (pessoaFisica.isPresent()) {
                object = pessoaFisica.get();
            } else if (pessoaJuridica.isPresent()) {
                object = pessoaJuridica.get();
            }

            return object;
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public PessoaFisica updatePf(PessoaFisicaUpdateDto pessoa) {
        try {
            Optional<PessoaFisica> clienteExiste = getIdPf(pessoa.getId());
            if(clienteExiste.isEmpty()) {
                throw new NegocioException("Cliente não encontrado ou inativo (ID:  " + pessoa.getId() + ").");
            }
            clienteMapper.toEntityUpdatePf(pessoa, clienteExiste.get());

            PessoaFisica pessoaFisica = _repositoryPessoaFisica.save(clienteExiste.get());
            if(pessoaFisica == null){
                throw new NegocioException("Não foi possível concluir a atualização do cliente.");
            }
            return pessoaFisica;
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public Pessoa updatePj(PessoaJuridicaUpdateDto pessoa) {
        try {
            Optional<PessoaJuridica> clienteExiste = getIdPj(pessoa.getId());
            if(clienteExiste.isEmpty()) {
                throw new NegocioException("Cliente não encontrado ou inativo (ID:  " + pessoa.getId() + ").");
            }
            clienteMapper.toEntityUpdatePj(pessoa, clienteExiste.get());

            PessoaJuridica pessoaJuridica = _repositoryPessoaJuridica.save(clienteExiste.get());
            if(pessoaJuridica == null){
                throw new NegocioException("Não foi possível concluir a atualização do cliente.");
            }
            return pessoaJuridica;
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    @Transactional
    public void inactivePf(PessoaFisica pessoaFisica) {
        try {
            if(pessoaFisica == null) {
                throw new NegocioException("Cliente não encontrado ou já inativo (ID:  " + pessoaFisica.getId() + ").");
            }
            pessoaFisica.setStatus(false);
            pessoaFisica.setAlterado_em(LocalDateTime.now());
            PessoaFisica pessoa = _repositoryPessoaFisica.save(pessoaFisica);
            if (pessoa == null) {
                throw new NegocioException("Não foi possível inativar o cliente (ID: " + pessoaFisica.getId() + ").");
            }
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public void inactivePJ(PessoaJuridica pessoaJuridica) {
        try {
            if(pessoaJuridica == null) {
                throw new NegocioException("Cliente não encontrado ou já inativo (ID:  " + pessoaJuridica.getId() + ").");
            }
            pessoaJuridica.setStatus(false);
            pessoaJuridica.setAlterado_em(LocalDateTime.now());
            PessoaJuridica pessoa = _repositoryPessoaJuridica.save(pessoaJuridica);
            if (pessoa == null) {
                throw new NegocioException("Não foi possível inativar o cliente (ID: " + pessoaJuridica.getId() + ").");
            }
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }
}
