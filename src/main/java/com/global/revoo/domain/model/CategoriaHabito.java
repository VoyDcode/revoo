package com.global.revoo.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORIA_HABITO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaHabito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA_HABITO")
    private Long id;

    @Column(name = "NOM_CATEGORIA", nullable = false, length = 100)
    private String nome;

    @Column(name = "DES_CATEGORIA", length = 400)
    private String descricao;

    @Column(name = "IND_ATIVO", nullable = false, length = 1)
    private String indicadorAtivo; // 'S' ou 'N'

    @Column(name = "DT_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DT_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "categoria")
    private List<Habito> habitos = new ArrayList<>();
}
