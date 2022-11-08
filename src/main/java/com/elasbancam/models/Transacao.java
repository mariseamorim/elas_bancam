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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_origem_id", referencedColumnName = "id")
    private Conta conta_origem_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_destino_id", referencedColumnName = "id")
    private Conta conta_destino_id;

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
