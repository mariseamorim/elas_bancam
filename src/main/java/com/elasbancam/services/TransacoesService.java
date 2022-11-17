package com.elasbancam.services;

import com.elasbancam.exceptions.NegocioException;
import com.elasbancam.models.Conta;
import com.elasbancam.models.Transacao;
import com.elasbancam.enums.TipoTransacao;
import com.elasbancam.repositories.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Service
public class TransacoesService {
    private TransacaoRepository _repositoryTransacao;
    private ContaService contaService;

    @Transactional
    public Transacao create(Transacao transacao) {
        try {
            List<Conta> contas = contaService.updateSaldo(transacao);
            if(contas.isEmpty()) {
                throw new NegocioException("Não foi possível concluir a transação.");
            }
            transacao.setConta_origem(contas.get(0));
            transacao.setConta_destino(contas.get(1));
            return _repositoryTransacao.save(transacao);
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public List<Transacao> getByType(TipoTransacao tipoTransacao){
        try {
            List<Transacao> transacoes = _repositoryTransacao.findByType(tipoTransacao.toString());
            if (transacoes.isEmpty()) {
                throw new NegocioException("Nenhuma transação do tipo " + tipoTransacao + " encontrada.");
            }
            return transacoes;
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public List<Transacao> getByDate(LocalDate dataInicial, LocalDate dataFinal){
        try {
            List<Transacao> transacoes = _repositoryTransacao.findByDate(dataInicial, dataFinal.plusDays(1));
            if(transacoes.isEmpty()){
                throw new NegocioException("Nenhuma transação encontrada entre " + dataInicial + " e " + dataFinal);
            }
            return transacoes;
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public List<Transacao> getByAccount(String id){
        try {
            contaService.getById(id);
            List<Transacao> transacoes = _repositoryTransacao.findByAccount(id);
            if(transacoes.isEmpty()){
                throw new NegocioException("Não foi encontrada nenhuma transação para a conta informada (ID: " + id + ").");
            }

            return transacoes;
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }

    public Transacao getById(String id){
        try {
            return _repositoryTransacao.findById(id).orElseThrow(() -> new NegocioException("Transação não encontrada (ID:  " + id + ")."));
        } catch(NegocioException negocioException) {
            throw new NegocioException(negocioException.getMessage());
        }
    }
}
