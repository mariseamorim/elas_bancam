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

@AllArgsConstructor
@Service
public class ContaService {
    private ContaRepository _repositoryConta;

    public Conta getById(String id) {

        return _repositoryConta.findById(id).orElseThrow(() -> new NegocioException("A conta informada não encontrada (ID: " + id + ")."));

    }

    @Transactional
    public List<Conta> updateSaldo(Transacao transacao) {
        String idContaOrigem = transacao.getConta_origem().getId();
        String idContaDestino = transacao.getConta_destino().getId();
        BigDecimal valor = transacao.getValor();

        Conta contaOrigem = getById(idContaOrigem);
        Conta contaDestino = getById(idContaDestino);

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
}
