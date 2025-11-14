package com.global.revoo.service;

import com.global.revoo.domain.enums.CategoriaHabito;
import com.global.revoo.domain.model.Habito;
import com.global.revoo.domain.repository.HabitoRepository;
import com.global.revoo.web.dto.habito.HabitoRequest;
import com.global.revoo.web.dto.habito.HabitoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HabitoService {

    private final HabitoRepository habitoRepository;

    public HabitoService(HabitoRepository habitoRepository) {
        this.habitoRepository = habitoRepository;
    }

    public HabitoResponse criar(HabitoRequest request) {
        Habito habito = new Habito(
                request.getTitulo(),
                request.getDescricao(),
                request.getCategoria(),
                request.getPontosBase()
        );
        if (request.getAtivo() != null) {
            habito.setAtivo(request.getAtivo());
        }
        Habito salvo = habitoRepository.save(habito);
        return toResponse(salvo);
    }

    public HabitoResponse buscarPorId(Long id) {
        Habito habito = habitoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hábito não encontrado"));
        return toResponse(habito);
    }

    public Page<HabitoResponse> listar(String titulo, CategoriaHabito categoria,
                                       Boolean ativo, Pageable pageable) {
        Page<Habito> page = habitoRepository.buscarComFiltro(
                titulo == null || titulo.isBlank() ? null : titulo,
                categoria,
                ativo,
                pageable
        );
        return page.map(this::toResponse);
    }

    public HabitoResponse atualizar(Long id, HabitoRequest request) {
        Habito habito = habitoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hábito não encontrado"));

        habito.setTitulo(request.getTitulo());
        habito.setDescricao(request.getDescricao());
        habito.setCategoria(request.getCategoria());
        habito.setPontosBase(request.getPontosBase());
        if (request.getAtivo() != null) {
            habito.setAtivo(request.getAtivo());
        }

        Habito atualizado = habitoRepository.save(habito);
        return toResponse(atualizado);
    }

    public void excluir(Long id) {
        if (!habitoRepository.existsById(id)) {
            throw new EntityNotFoundException("Hábito não encontrado");
        }
        habitoRepository.deleteById(id);
    }

    private HabitoResponse toResponse(Habito habito) {
        return new HabitoResponse(
                habito.getId(),
                habito.getTitulo(),
                habito.getDescricao(),
                habito.getCategoria(),
                habito.getPontosBase(),
                habito.getAtivo()
        );
    }
}
