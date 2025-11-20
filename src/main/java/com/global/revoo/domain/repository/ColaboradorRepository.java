package com.global.revoo.domain.repository;

import com.global.revoo.domain.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    // Exemplo: buscar por email
    boolean existsByEmail(String email);
}
