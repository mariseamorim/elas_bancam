package com.elasbancam.repositories;

import com.elasbancam.models.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
    @Query(value = "SELECT * FROM pessoa_juridica p WHERE p.status = 1", nativeQuery = true)
    List<PessoaJuridica> findAll();

    @Query(value = "SELECT * FROM pessoa_juridica  p WHERE p.id = ?1 and p.status = 1", nativeQuery = true)
    Optional<PessoaJuridica> findById(Long id);
}
