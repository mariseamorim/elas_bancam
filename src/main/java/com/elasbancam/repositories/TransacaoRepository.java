package com.elasbancam.repositories;

import com.elasbancam.models.Transacao;
import com.elasbancam.enums.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, String> {
    @Query(value = "SELECT * FROM transacao t WHERE t.tipo_transacao = tipoTransacao;", nativeQuery = true)
    List<Transacao> findByType(TipoTransacao tipoTransacao);

    @Query(value = "SELECT * FROM transacao t WHERE t.data BETWEEN dataInicial AND dataFinal;", nativeQuery = true)
    List<Transacao> findByDate(LocalDateTime dataInicial, LocalDateTime dataFinal);

    @Query(value = "SELECT * FROM transacao t WHERE t.conta_origem_id = id OR t.conta_destino_id = id;", nativeQuery = true)
    List<Transacao> findByAccount(String id);
}
