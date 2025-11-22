package com.global.revoo.domain.model;

import com.global.revoo.domain.enums.StatusMeta;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "META_SEMANAL")
public class MetaSemanal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_META_SEMANAL")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_COLABORADOR", nullable = false)
    private Colaborador colaborador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_HABITO", nullable = false)
    private Habito habito;

    @Column(name = "DT_SEMANA_INICIO", nullable = false)
    private LocalDate semanaInicio;

    @Column(name = "DT_SEMANA_FIM", nullable = false)
    private LocalDate semanaFim;

    @Column(name = "QTD_META_SEMANAL", nullable = false, precision = 8, scale = 2)
    private BigDecimal quantidadeMeta;

    @Enumerated(EnumType.STRING)
    @Column(name = "IND_STATUS", nullable = false, length = 20)
    private StatusMeta status = StatusMeta.ATIVA;

    @Column(name = "DT_CRIACAO", insertable = false, updatable = false)
    private LocalDate dataCriacao;

    @Column(name = "DT_ATUALIZACAO", insertable = false, updatable = false)
    private LocalDate dataAtualizacao;

    public MetaSemanal() {
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Habito getHabito() {
        return habito;
    }

    public void setHabito(Habito habito) {
        this.habito = habito;
    }

    public LocalDate getSemanaInicio() {
        return semanaInicio;
    }

    public void setSemanaInicio(LocalDate semanaInicio) {
        this.semanaInicio = semanaInicio;
    }

    public LocalDate getSemanaFim() {
        return semanaFim;
    }

    public void setSemanaFim(LocalDate semanaFim) {
        this.semanaFim = semanaFim;
    }

    public BigDecimal getQuantidadeMeta() {
        return quantidadeMeta;
    }

    public void setQuantidadeMeta(BigDecimal quantidadeMeta) {
        this.quantidadeMeta = quantidadeMeta;
    }

    public StatusMeta getStatus() {
        return status;
    }

    public void setStatus(StatusMeta status) {
        this.status = status;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
