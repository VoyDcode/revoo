package com.global.revoo.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "COLABORADOR") // 🔁 troque para o nome da tabela real no Oracle
public class Colaborador {

    @Id
    @Column(name = "ID_COLABORADOR") // coluna PK da tabela
    // Se tiver SEQUENCE no banco:
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COLABORADOR_SEQ")
    // @SequenceGenerator(name = "COLABORADOR_SEQ", sequenceName = "COLABORADOR_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "EMAIL", nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "DEPARTAMENTO", length = 80)
    private String departamento;

    // getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
