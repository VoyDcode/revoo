package com.global.revoo.domain.model;

import com.global.revoo.domain.enums.StatusMeta;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "META_SEMANAL")
public class MetaSemanal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_META_SEMANAL")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_HABITO", nullable = false)
    private Habito habito;

    @Column(name = "QTD_ALVO", nullable = false)
    private Integer quantidadeAlvo;

    @Column(name = "DATA_INICIO", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "DATA_FIM", nullable = false)
    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 20)
    private StatusMeta status = StatusMeta.ATIVA;

    public MetaSemanal() {}

    // getters/setters...
}
