package com.elasbancam.models;

import com.elasbancam.models.enums.TipoPessoa;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter

@MappedSuperclass
public abstract  class Pessoa {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(length = 50, nullable = false)
    private String nome;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 20, nullable = false)
    private String telefone;
    @Column(length = 20, nullable = false)
    private String celular;
    @Column(length = 20, nullable = false)
    private Date criado_em;
    @Enumerated(EnumType.ORDINAL)
    private TipoPessoa tipo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

}
