package com.global.revoo.web.dto.habito;

import com.global.revoo.domain.model.CategoriaHabito;

public class HabitoResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private CategoriaHabito categoria;
    private Integer pontosBase;
    private Boolean ativo;

    public HabitoResponse(Long id, String titulo, String descricao,
                          CategoriaHabito categoria, Integer pontosBase, Boolean ativo) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.pontosBase = pontosBase;
        this.ativo = ativo;
    }

    // getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public CategoriaHabito getCategoria() { return categoria; }
    public Integer getPontosBase() { return pontosBase; }
    public Boolean getAtivo() { return ativo; }
}
