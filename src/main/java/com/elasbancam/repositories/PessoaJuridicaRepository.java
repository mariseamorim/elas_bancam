package com.elasbancam.repositories;

import com.elasbancam.models.PessoaFisica;
import com.elasbancam.models.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Integer> {
}
