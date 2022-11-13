package com.elasbancam.repositories;

import com.elasbancam.models.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {
    @Query(value = "SELECT * FROM pessoa_fisica p WHERE p.status = 1", nativeQuery = true)
    List<PessoaFisica> findAll();

    @Query(value = "SELECT * FROM pessoa_fisica p WHERE p.id = ?1 AND p.status = 1", nativeQuery = true)
    Optional<PessoaFisica> findById(Long id);

}
