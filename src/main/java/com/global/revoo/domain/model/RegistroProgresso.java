package com.global.revoo.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "REGISTRO_PROGRESSO")
public class RegistroProgresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REGISTRO_PROGRESSO")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_META_SEMANAL", nullable = false)
    private MetaSemanal metaSemanal;

    @Column(name = "DATA_REGISTRO", nullable = false)
    private LocalDate dataRegistro;

    @Column(name = "QTD_REALIZADA", nullable = false, precision = 8, scale = 2)
    private BigDecimal quantidadeProgresso;

    @Column(name = "OBS")
    private String observacao;

    @Column(name = "DT_CRIACAO", insertable = false, updatable = false)
    private LocalDate dataCriacao;

    @Column(name = "DT_ATUALIZACAO", insertable = false, updatable = false)
    private LocalDate dataAtualizacao;

    public RegistroProgresso() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MetaSemanal getMetaSemanal() {
        return metaSemanal;
    }

    public void setMetaSemanal(MetaSemanal metaSemanal) {
        this.metaSemanal = metaSemanal;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public BigDecimal getQuantidadeProgresso() {
        return quantidadeProgresso;
    }

    public void setQuantidadeProgresso(BigDecimal quantidadeProgresso) {
        this.quantidadeProgresso = quantidadeProgresso;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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
