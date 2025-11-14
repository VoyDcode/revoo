package com.global.revoo.web.dto.habito;

import com.global.revoo.domain.enums.CategoriaHabito;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HabitoRequest {

    @NotBlank
    @Size(max = 120)
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @NotNull
    private CategoriaHabito categoria;

    @NotNull
    @Min(1)
    private Integer pontosBase;

    private Boolean ativo = true;

    // getters/setters
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
