package com.elasbancam.services;

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

    @Transactional
    public PessoaFisica savePf(PessoaFisica pessoaFisica) {
        try {
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
        PessoaJuridica resposta = new PessoaJuridica();
        try {
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
            var pf = _repositoryPessoaFisica.findAll();
            var pj = _repositoryPessoaJuridica.findAll();
            object.add(pf);
            object.add(pj);
            if(object.isEmpty()){
                throw new NegocioException("Não há nenhum cliente cadastrado.");
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
            if (pessoaFisica.isPresent()) {
                object = pessoaFisica.get();
            }
            if (pessoaJuridica.isPresent()) {
                object = pessoaJuridica.get();
            }
            if(object == null) {
                throw new NegocioException("Cliente não encontrado (ID:  " + idCliente + ").");
            }
            return object;
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public PessoaFisica updatePf(PessoaFisica cliente) {
        try {
            if(cliente == null) {
                throw new NegocioException("Cliente não encontrado (ID:  " + cliente.getId() + ").");
            }
            PessoaFisica pessoaFisica = _repositoryPessoaFisica.save(cliente);
            if(pessoaFisica == null){
                throw new NegocioException("Não foi possível concluir a atualização do cliente.");
            }
            return pessoaFisica;
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public Pessoa updatePj(PessoaJuridica cliente) {
        try {
            if(cliente == null) {
                throw new NegocioException("Cliente não encontrado (ID:  " + cliente.getId() + ").");
            }
            PessoaJuridica pessoaJuridica = _repositoryPessoaJuridica.save(cliente);
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
            pessoaFisica.setStatus(false);
            pessoaFisica.setAlterado_em(LocalDateTime.now());
            PessoaFisica pessoa = savePf(pessoaFisica);
            if (pessoa == null) {
                throw new NegocioException("Não foi possível inativar o cliente (ID: " + pessoaFisica.getId() + ").");
            }
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public void inactivePJ(PessoaJuridica pessoaJuridica) {
        try {
            pessoaJuridica.setStatus(false);
            pessoaJuridica.setAlterado_em(LocalDateTime.now());
            PessoaJuridica pessoa = savePj(pessoaJuridica);
            if (pessoa == null) {
                throw new NegocioException("Não foi possível inativar o cliente (ID: " + pessoaJuridica.getId() + ").");
            }
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }
}
