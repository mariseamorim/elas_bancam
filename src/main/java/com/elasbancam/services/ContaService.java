package com.elasbancam.services;

import com.elasbancam.exceptions.NegocioException;
import com.elasbancam.models.Conta;
import com.elasbancam.models.Transacao;
import com.elasbancam.repositories.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Os métodos das camadas de service fazem validações de regras de negócio e lançam erros para que os
// controllers os tratem.

@AllArgsConstructor
@Service
public class ContaService {
    private ContaRepository _repositoryConta;

    public Conta buscarContaPorId(String id) {
        Conta conta = _repositoryConta.buscarPorId(id);
        if(conta == null) {
            throw new NegocioException("A conta informada não encontrada ou está inativa (ID: " + id + ").");
        }
        return conta;
    }

    @Transactional
    public List<Conta> atualizarSaldo(Transacao transacao) {
        String idContaOrigem = transacao.getConta_origem().getId();
        String idContaDestino = transacao.getConta_destino().getId();
        BigDecimal valor = transacao.getValor();

        Conta contaOrigem = buscarContaPorId(idContaOrigem);
        Conta contaDestino = buscarContaPorId(idContaDestino);

        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            throw new NegocioException("Saldo insuficiente para realizar transação");
        } else {
            List<Conta> listaContas = new ArrayList<>();
            contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
            contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
            listaContas.add(contaOrigem);
            listaContas.add(contaDestino);
            return listaContas;
        }
    }

    public Conta inativarConta(String id) {
        Conta conta = buscarContaPorId(id);

        try {
            conta.setStatus(false);
            return _repositoryConta.save(conta);
        } catch (NegocioException e) {
            throw new NegocioException("Não foi possível inativar o cliente (ID: " + id + ").");
        }

    }
}
