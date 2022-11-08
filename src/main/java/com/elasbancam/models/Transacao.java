package com.elasbancam.models;

import com.elasbancam.models.enums.TipoTransacao;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_origem", referencedColumnName = "id")
    @Column(nullable = false)
    private Conta conta_origem;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_destino", referencedColumnName = "id")
    @Column(nullable = false)
    private Conta conta_destino;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TipoTransacao tipo_trasacao;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime data;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column()
    private String descricao;
}
