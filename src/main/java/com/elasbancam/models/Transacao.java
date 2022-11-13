package com.elasbancam.models;

import com.elasbancam.enums.TipoTransacao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "transacao")
public class Transacao {
    @Getter
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(insertable = false, unique = true, updatable = false, nullable = false)
    private String id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conta_origem_id", referencedColumnName = "id")
    private Conta conta_origem_id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conta_destino_id", referencedColumnName = "id")
    private Conta conta_destino_id;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransacao tipo_transacao ;

    @Getter
    @Setter
    @Column(nullable = false)
    @CreationTimestamp
    private Date data;

    @Getter
    @Setter
    @Column(nullable = false)
    private BigDecimal valor;

    @Getter
    @Setter
    @Column()
    private String descricao;
}
