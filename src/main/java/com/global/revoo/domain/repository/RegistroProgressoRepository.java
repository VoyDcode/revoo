package com.global.revoo.domain.repository;

import com.global.revoo.domain.model.RegistroProgresso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RegistroProgressoRepository extends JpaRepository<RegistroProgresso, Long> {

    List<RegistroProgresso> findByMetaSemanalId(Long idMetaSemanal);

    List<RegistroProgresso> findByMetaSemanalIdAndDataRegistroBetween(
            Long idMetaSemanal,
            LocalDate dataInicio,
            LocalDate dataFim
    );
}
