package com.global.revoo.domain.repository;

import com.global.revoo.domain.enums.CategoriaHabito;
import com.global.revoo.domain.model.Habito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HabitoRepository extends JpaRepository<Habito, Long> {

    @Query("""
            SELECT h FROM Habito h
            WHERE (:titulo IS NULL OR LOWER(h.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
              AND (:categoria IS NULL OR h.categoria = :categoria)
              AND (:ativo IS NULL OR h.ativo = :ativo)
            """)
    Page<Habito> buscarComFiltro(
            @Param("titulo") String titulo,
            @Param("categoria") CategoriaHabito categoria,
            @Param("ativo") Boolean ativo,
            Pageable pageable
    );
}
