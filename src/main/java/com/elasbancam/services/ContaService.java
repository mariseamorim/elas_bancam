package com.elasbancam.services;

import com.elasbancam.models.Conta;
import com.elasbancam.models.Transacao;
import com.elasbancam.repositories.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContaService {
    private ContaRepository _repositoryConta;

    public Optional<Conta> getById(String id){
        return _repositoryConta.findById(id);
    }

    @Transactional
    public void updateSaldo(Transacao transacao) {
        String conta_origem_id = transacao.getConta_origem_id().getId();
        String conta_destino_id = transacao.getConta_destino_id().getId();
        BigDecimal valor = transacao.getValor();

        Optional<Conta> contaOrigem = getById(conta_origem_id);
        Optional<Conta> contaDestino = getById(conta_destino_id);

        if(contaOrigem.isPresent() && contaDestino.isPresent()) {
            Conta objetoContaOrigem = contaOrigem.get();
            Conta objetoContaDestino = contaDestino.get();

            if(objetoContaOrigem.getSaldo().compareTo(valor) >= 0) {
                objetoContaOrigem.setSaldo(objetoContaOrigem.getSaldo().subtract(valor));
                objetoContaDestino.setSaldo(objetoContaDestino.getSaldo().add(valor));
            }
        }
    }
}
