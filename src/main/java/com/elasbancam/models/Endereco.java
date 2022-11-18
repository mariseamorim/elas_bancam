package com.elasbancam.models;

import com.elasbancam.enums.Regiao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

// Representa a tabela "endereco"
@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(length = 10, nullable = false)
    private String cep;

    @Getter
    @Setter
    @Column(nullable = false)
    private String rua;

    @Getter
    @Setter
    @Column(nullable = false)
    private int numero;

    @Getter
    @Setter
    @Column(length = 20)
    private String complemento;

    @Getter
    @Setter
    @Column
    private String bairro;

    @Getter
    @Setter
    @Column
    private String cidade;

    @Getter
    @Setter
    @Column(length = 2)
    private String uf;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(length = 2)
    private Regiao regiao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
