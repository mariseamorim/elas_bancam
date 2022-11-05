package com.elasbancam.repositories;

import com.elasbancam.models.Pessoa;
import com.elasbancam.models.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer> {

}
