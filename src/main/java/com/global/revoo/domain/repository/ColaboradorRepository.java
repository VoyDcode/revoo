package com.global.revoo.domain.repository;

import com.global.revoo.domain.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    boolean existsByMatricula(String matricula);

    boolean existsByUsuarioId(Long usuarioId);
}
