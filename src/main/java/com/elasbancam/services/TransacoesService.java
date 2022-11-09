package com.elasbancam.services;

import com.elasbancam.dtos.DataTransacaoDto;
import com.elasbancam.models.Transacao;
import com.elasbancam.models.enums.TipoTransacao;
import com.elasbancam.repositories.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Component
@Service
public class TransacoesService {
    private TransacaoRepository _repositoryTransacao;

    @Transactional
    public Transacao save(Transacao transacao) {
        //Fazer regra de validacao e atualizacao de saldo
        return _repositoryTransacao.save(transacao);
    }


    public List<Transacao> getByType(TipoTransacao tipoTransacao){
        var resposta = _repositoryTransacao.findByType(tipoTransacao);
        return resposta;
    }

    public List<Transacao> getByDate(LocalDateTime dataInicial, LocalDateTime dataFinal){
        var resposta = _repositoryTransacao.findByDate(dataInicial, dataFinal);
        return resposta;
    }

    public List<Transacao> getByAccount(int id){
        //Buscar informação nas colunas de conta de origem e destino
        var resposta = _repositoryTransacao.findByAccount(id);
        return resposta;
    }

    public Optional<Transacao> getById(UUID id){
        var resposta = _repositoryTransacao.findById(id);
        return resposta;
    }

}
