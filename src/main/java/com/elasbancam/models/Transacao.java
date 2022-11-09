package com.elasbancam.models;

import com.elasbancam.models.enums.TipoTransacao;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    private UUID id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_origem_id", referencedColumnName = "id")
    private Conta conta_origem_id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_destino_id", referencedColumnName = "id")
    private Conta conta_destino_id;

    @Getter
    @Setter
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TipoTransacao tipo_trasacao;

    @Getter
    @Setter
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime data;

    @Getter
    @Setter
    @Column(nullable = false)
    private BigDecimal valor;

    @Getter
    @Setter
    @Column()
    private String descricao;
}
