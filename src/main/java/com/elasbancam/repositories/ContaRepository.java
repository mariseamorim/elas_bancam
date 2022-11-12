package com.elasbancam.repositories;

import com.elasbancam.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository  extends JpaRepository<Conta, String> {
}
