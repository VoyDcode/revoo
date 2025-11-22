package com.global.revoo.web.dto.meta;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MetaSemanalRequest {

    @NotNull
    private Long idColaborador;

    @NotNull
    private Long idHabito;

    @NotNull
    private LocalDate semanaInicio;

    @NotNull
    private LocalDate semanaFim;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal quantidadeMeta;

    public Long getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Long idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Long getIdHabito() {
        return idHabito;
    }

    public void setIdHabito(Long idHabito) {
        this.idHabito = idHabito;
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
}
