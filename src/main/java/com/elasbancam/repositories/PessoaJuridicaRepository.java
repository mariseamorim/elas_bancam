package com.elasbancam.repositories;

import com.elasbancam.models.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Foi escolhido passar o query em linguagem nativa SQL, para treinarmos DQL (Data Query Language)
@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
    @Query(value = "SELECT * FROM pessoa_juridica p WHERE p.status = 1", nativeQuery = true)
    List<PessoaJuridica> listarTodosPJ();

    @Query(value = "SELECT * FROM pessoa_juridica  p WHERE p.id = ?1 and p.status = 1", nativeQuery = true)
    Optional<PessoaJuridica> buscarPjPorId(Long id);

    @Query(value = "SELECT * FROM pessoa_juridica  p WHERE p.cnpj = ?1", nativeQuery = true)
    Optional<PessoaJuridica> buscarPjPorCNPJ(String CNPJ);

    @Query(value = "SELECT * FROM pessoa_juridica  p WHERE p.inscricao_estadual = ?1", nativeQuery = true)
    Optional<PessoaJuridica> buscarPjPorInscricao(String inscricaoEstadual);
}
