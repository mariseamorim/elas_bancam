package com.elasbancam.services;

import com.elasbancam.models.Conta;
import com.elasbancam.models.Transacao;
import com.elasbancam.enums.TipoTransacao;
import com.elasbancam.repositories.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@AllArgsConstructor
@Service
public class TransacoesService {
    private TransacaoRepository _repositoryTransacao;
    private ContaService contaService;

    @Transactional
    public Optional<Transacao> create(Transacao transacao) {
        contaService.updateSaldo(transacao);
        Transacao transacaoCriada = _repositoryTransacao.save(transacao);
        return getById(transacaoCriada.getId());
    }

    public List<Transacao> getByType(TipoTransacao tipoTransacao){
        return _repositoryTransacao.findByType(tipoTransacao.toString());
    }

    public List<Transacao> getByDate(String dataInicial, String dataFinal){
        return _repositoryTransacao.findByDate(dataInicial, dataFinal);
    }

    public List<Transacao> getByAccount(String id){
        Optional<Conta> contaExiste = contaService.getById(id);
        //Validar id passado através do service de conta (CRIAR IF QUANDO TIVER A EXCEPTION)
        return _repositoryTransacao.findByAccount(id);
    }

    public Optional<Transacao> getById(String id){
        return _repositoryTransacao.findById(id);
    }

}
