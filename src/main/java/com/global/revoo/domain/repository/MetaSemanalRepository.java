package com.global.revoo.domain.repository;

import com.global.revoo.domain.enums.StatusMeta;
import com.global.revoo.domain.model.MetaSemanal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MetaSemanalRepository extends JpaRepository<MetaSemanal, Long> {

    List<MetaSemanal> findByColaboradorId(Long colaboradorId);

    List<MetaSemanal> findByColaboradorIdAndStatus(Long colaboradorId, StatusMeta status);

    Optional<MetaSemanal> findByIdAndColaboradorId(Long id, Long colaboradorId);
}
