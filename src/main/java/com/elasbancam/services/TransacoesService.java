package com.elasbancam.services;

import com.elasbancam.models.Transacao;
import com.elasbancam.enums.TipoTransacao;
import com.elasbancam.repositories.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class TransacoesService {
    private TransacaoRepository _repositoryTransacao;
    private ContaService contaService;

    @Transactional
    public Transacao save(Transacao transacao) {
        contaService.updateSaldo(transacao);
        return _repositoryTransacao.save(transacao);
    }


    public List<Transacao> getByType(TipoTransacao tipoTransacao){
        return _repositoryTransacao.findByType(tipoTransacao);
    }

    public List<Transacao> getByDate(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return _repositoryTransacao.findByDate(dataInicial, dataFinal);
    }

    public List<Transacao> getByAccount(String id){
        //Validar id passado através do service de conta
        return _repositoryTransacao.findByAccount(id);
    }

    public Optional<Transacao> getById(String id){
        return _repositoryTransacao.findById(id);
    }

}
