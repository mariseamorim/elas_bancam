package com.elasbancam.repositories;

import com.elasbancam.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContaRepository  extends JpaRepository<Conta, String> {
    @Query(value = "SELECT * FROM conta p WHERE p.id = ?1 AND p.status = 1", nativeQuery = true)
    Conta buscarPorId(String id);

    @Query(value = "SELECT * FROM conta p WHERE p.numero_conta = ?1", nativeQuery = true)
    Conta buscarPorNumeroConta(int numeroConta);
}
