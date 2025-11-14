package com.global.revoo.domain.model;

import com.global.revoo.domain.enums.CategoriaHabito;
import jakarta.persistence.*;

@Entity
@Table(name = "HABITO")
public class Habito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HABITO")
    private Long id;

    @Column(name = "TITULO", nullable = false, length = 120)
    private String titulo;

    @Column(name = "DESCRICAO", nullable = false, length = 500)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA", nullable = false, length = 30)
    private CategoriaHabito categoria;

    @Column(name = "PONTOS_BASE", nullable = false)
    private Integer pontosBase;

    @Column(name = "ATIVO", nullable = false)
    private Boolean ativo = true;

    public Habito() {}

    public Habito(String titulo, String descricao, CategoriaHabito categoria, Integer pontosBase) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.pontosBase = pontosBase;
        this.ativo = true;
    }

    // Getters e setters
    public Long getId() { return id; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public CategoriaHabito getCategoria() { return categoria; }

    public void setCategoria(CategoriaHabito categoria) { this.categoria = categoria; }

    public Integer getPontosBase() { return pontosBase; }

    public void setPontosBase(Integer pontosBase) { this.pontosBase = pontosBase; }

    public Boolean getAtivo() { return ativo; }

    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
