package com.global.revoo.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "COLABORADOR")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COLABORADOR")
    private Long id;

    // FK para USUARIO (ID_USUARIO)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "MATRICULA", length = 30, unique = true)
    private String matricula;

    @Column(name = "CARGO", length = 60)
    private String cargo;

    @Column(name = "SETOR", length = 60)
    private String setor;

    @Column(name = "DT_ADMISSAO")
    private LocalDate dataAdmissao;

    @Column(name = "DT_DESLIGAMENTO")
    private LocalDate dataDesligamento;

    // Preenchidas pelas triggers / pelo banco
    @Column(name = "DT_CRIACAO", insertable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DT_ATUALIZACAO", insertable = false, updatable = false)
    private LocalDateTime dataAtualizacao;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataDesligamento() {
        return dataDesligamento;
    }

    public void setDataDesligamento(LocalDate dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
