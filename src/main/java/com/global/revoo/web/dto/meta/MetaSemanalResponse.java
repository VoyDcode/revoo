package com.global.revoo.web.dto.meta;

import com.global.revoo.domain.enums.StatusMeta;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MetaSemanalResponse {

    private Long id;
    private Long idColaborador;
    private String nomeColaborador;
    private Long idHabito;
    private String tituloHabito;

    private LocalDate semanaInicio;
    private LocalDate semanaFim;
    private BigDecimal quantidadeMeta;
    private StatusMeta status;

    public MetaSemanalResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Long idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public void setNomeColaborador(String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
    }

    public Long getIdHabito() {
        return idHabito;
    }

    public void setIdHabito(Long idHabito) {
        this.idHabito = idHabito;
    }

    public String getTituloHabito() {
        return tituloHabito;
    }

    public void setTituloHabito(String tituloHabito) {
        this.tituloHabito = tituloHabito;
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
}
