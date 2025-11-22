package com.global.revoo.web.dto.progresso;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RegistroProgressoRequest {

    @NotNull
    private Long idMetaSemanal;

    @NotNull
    private LocalDate dataRegistro;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal quantidadeProgresso;

    private String observacao;

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
}
