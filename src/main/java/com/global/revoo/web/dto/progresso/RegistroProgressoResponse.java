package com.global.revoo.web.dto.progresso;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RegistroProgressoResponse {

    private Long id;
    private Long idMetaSemanal;
    private LocalDate dataRegistro;
    private BigDecimal quantidadeProgresso;
    private String observacao;

    // opcional: informações agregadas
    private LocalDate semanaInicio;
    private LocalDate semanaFim;

    public RegistroProgressoResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMetaSemanal() {
        return idMetaSemanal;
    }

    public void setIdMetaSemanal(Long idMetaSemanal) {
        this.idMetaSemanal = idMetaSemanal;
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
}
