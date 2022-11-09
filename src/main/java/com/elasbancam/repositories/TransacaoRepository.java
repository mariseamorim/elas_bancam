package com.elasbancam.repositories;

import com.elasbancam.dtos.DataTransacaoDto;
import com.elasbancam.models.Transacao;
import com.elasbancam.models.enums.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
    List<Transacao> findByType(TipoTransacao tipoTransacao);

    List<Transacao> findByDate(LocalDateTime dataInicial, LocalDateTime dataFinal);

    List<Transacao> findByAccount(int id);
}
