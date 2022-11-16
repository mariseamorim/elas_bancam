package com.elasbancam.services;

import com.elasbancam.models.Conta;
import com.elasbancam.models.Transacao;
import com.elasbancam.repositories.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContaService {
    private ContaRepository _repositoryConta;

    public Optional<Conta> getById(String id){
        return _repositoryConta.findById(id);
    }

    @Transactional
    public List<Conta> updateSaldo(Transacao transacao) {
        List<Conta> listaContas = new ArrayList<>();
        try {
            String idContaOrigem = transacao.getConta_origem().getId();
            String idContaDestino = transacao.getConta_destino().getId();
            BigDecimal valor = transacao.getValor();

            Optional<Conta> contaOrigem = getById(idContaOrigem);
            Optional<Conta> contaDestino = getById(idContaDestino);

            if(contaOrigem.isPresent() && contaDestino.isPresent()) {
                Conta objetoContaOrigem = contaOrigem.get();
                Conta objetoContaDestino = contaDestino.get();

                if(objetoContaOrigem.getSaldo().compareTo(valor) >= 0) {
                    objetoContaOrigem.setSaldo(objetoContaOrigem.getSaldo().subtract(valor));
                    objetoContaDestino.setSaldo(objetoContaDestino.getSaldo().add(valor));
                }

                listaContas.add(contaOrigem.get());
                listaContas.add(contaDestino.get());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaContas;
    }
}
