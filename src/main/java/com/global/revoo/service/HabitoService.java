package com.global.revoo.service;

import com.global.revoo.domain.model.CategoriaHabito;
import com.global.revoo.domain.model.Habito;
import com.global.revoo.domain.repository.HabitoRepository;
import com.global.revoo.web.dto.habito.HabitoRequest;
import com.global.revoo.web.dto.habito.HabitoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class HabitoService {

    private final HabitoRepository habitoRepository;

    public HabitoService(HabitoRepository habitoRepository) {
        this.habitoRepository = habitoRepository;
    }

    public HabitoResponse criar(HabitoRequest request) {
        
        Habito habito = new Habito();
        
        habito.setNome(request.getTitulo());
        habito.setDescricao(request.getDescricao());
        habito.setCategoria(request.getCategoria());
        habito.setPontosBase(request.getPontosBase());
        
        Boolean ativoRequest = request.getAtivo();
        String ativo = (ativoRequest == null || ativoRequest) ? "S" : "N";
        habito.setAtivo(ativo);
        // Define data de criação agora (caso não seja preenchida pelo banco).
        habito.setDataCriacao(LocalDateTime.now());
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
        // Normaliza o filtro de título
        String filtroTitulo = (titulo == null || titulo.isBlank()) ? null : titulo;
        
        String indicadorAtivo = null;
        if (ativo != null) {
            indicadorAtivo = ativo ? "S" : "N";
        }
        Page<Habito> page = habitoRepository.buscarComFiltro(
                filtroTitulo,
                categoria,
                indicadorAtivo,
                pageable
        );
        return page.map(this::toResponse);
    }

    public HabitoResponse atualizar(Long id, HabitoRequest request) {
        Habito habito = habitoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hábito não encontrado"));

        // Atualiza campos da entidade a partir do DTO
        habito.setNome(request.getTitulo());
        habito.setDescricao(request.getDescricao());
        habito.setCategoria(request.getCategoria());
        habito.setPontosBase(request.getPontosBase());
        if (request.getAtivo() != null) {
            String ativoAtualizado = request.getAtivo() ? "S" : "N";
            habito.setAtivo(ativoAtualizado);
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
        // Converte o indicador 'S'/'N' da entidade para booleano na resposta
        Boolean ativo = null;
        if (habito.getAtivo() != null) {
            ativo = "S".equalsIgnoreCase(habito.getAtivo());
        }
        return new HabitoResponse(
                habito.getId(),
                habito.getNome(),
                habito.getDescricao(),
                habito.getCategoria(),
                habito.getPontosBase(),
                ativo
        );
    }
}
