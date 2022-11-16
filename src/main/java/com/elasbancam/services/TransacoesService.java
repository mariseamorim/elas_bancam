package com.elasbancam.services;

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
        Transacao resposta = new Transacao();
        try {
            List<Conta> contas = contaService.updateSaldo(transacao);
            transacao.setConta_origem(contas.get(0));
            transacao.setConta_destino(contas.get(1));
            resposta =  _repositoryTransacao.save(transacao);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    public List<Transacao> getByType(TipoTransacao tipoTransacao){
        List<Transacao> resposta = new ArrayList<>();
        try {
            resposta = _repositoryTransacao.findByType(tipoTransacao.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    public List<Transacao> getByDate(LocalDate dataInicial, LocalDate dataFinal){
        List<Transacao> resposta = new ArrayList<>();
        try {
            resposta = _repositoryTransacao.findByDate(dataInicial, dataFinal.plusDays(1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    public List<Transacao> getByAccount(String id){
        List<Transacao> resposta = new ArrayList<>();
        try {
            Optional<Conta> contaExiste = contaService.getById(id);
            //Validar id passado através do service de conta (CRIAR IF QUANDO TIVER A EXCEPTION)
            resposta = _repositoryTransacao.findByAccount(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }

    public Optional<Transacao> getById(String id){
        Optional<Transacao> resposta = Optional.of(new Transacao());
        try {
            resposta = _repositoryTransacao.findById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resposta;
    }
}
