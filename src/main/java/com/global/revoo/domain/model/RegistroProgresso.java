package com.global.revoo.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "REGISTRO_PROGRESSO")
public class RegistroProgresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REGISTRO_PROGRESSO")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_META_SEMANAL", nullable = false)
    private MetaSemanal metaSemanal;

    @Column(name = "DATA_HORA_REGISTRO", nullable = false)
    private LocalDateTime dataHoraRegistro;

    @Column(name = "QTD_REALIZADA", nullable = false)
    private Integer quantidadeRealizada;

    @Column(name = "PONTOS_GERADOS", nullable = false)
    private Integer pontosGerados;

    @Column(name = "OBSERVACAO", length = 300)
    private String observacao;

    public RegistroProgresso() {}

    // getters/setters...
}
