package com.global.revoo.service;

import com.global.revoo.domain.enums.StatusMeta;
import com.global.revoo.domain.model.Colaborador;
import com.global.revoo.domain.model.Habito;
import com.global.revoo.domain.model.MetaSemanal;
import com.global.revoo.domain.repository.ColaboradorRepository;
import com.global.revoo.domain.repository.HabitoRepository;
import com.global.revoo.domain.repository.MetaSemanalRepository;
import com.global.revoo.web.dto.meta.MetaSemanalRequest;
import com.global.revoo.web.dto.meta.MetaSemanalResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MetaSemanalService {

    private final MetaSemanalRepository metaSemanalRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final HabitoRepository habitoRepository;

    public MetaSemanalService(MetaSemanalRepository metaSemanalRepository,
                              ColaboradorRepository colaboradorRepository,
                              HabitoRepository habitoRepository) {
        this.metaSemanalRepository = metaSemanalRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.habitoRepository = habitoRepository;
    }

    public List<MetaSemanalResponse> listarTodos() {
        return metaSemanalRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public MetaSemanalResponse buscarPorId(Long id) {
        MetaSemanal meta = metaSemanalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meta semanal não encontrada"));
        return toResponse(meta);
    }

    public List<MetaSemanalResponse> listarPorColaborador(Long idColaborador) {
        return metaSemanalRepository.findByColaboradorId(idColaborador)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public MetaSemanalResponse criar(MetaSemanalRequest request) {
        Colaborador colaborador = colaboradorRepository.findById(request.getIdColaborador())
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

        Habito habito = habitoRepository.findById(request.getIdHabito())
                .orElseThrow(() -> new RuntimeException("Hábito não encontrado"));

        MetaSemanal meta = new MetaSemanal();
        meta.setColaborador(colaborador);
        meta.setHabito(habito);
        meta.setSemanaInicio(request.getSemanaInicio());
        meta.setSemanaFim(request.getSemanaFim());
        meta.setQuantidadeMeta(request.getQuantidadeMeta());
        meta.setStatus(StatusMeta.ATIVA);

        try {
            MetaSemanal salva = metaSemanalRepository.save(meta);
            return toResponse(salva);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage() != null && e.getMessage().contains("UK_META_COLAB_HABITO_SEM")) {
                throw new IllegalArgumentException(
                    "Já existe uma meta semanal para este colaborador, hábito e semana de início. " +
                    "Não é permitido criar metas duplicadas para a mesma semana."
                );
            }
            throw e;
        }
    }

    public void excluir(Long id) {
        MetaSemanal meta = metaSemanalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meta semanal não encontrada"));
        metaSemanalRepository.delete(meta);
    }

    private MetaSemanalResponse toResponse(MetaSemanal meta) {
        MetaSemanalResponse response = new MetaSemanalResponse();
        response.setId(meta.getId());

        Colaborador colaborador = meta.getColaborador();
        if (colaborador != null) {
            response.setIdColaborador(colaborador.getId());
            if (colaborador.getUsuario() != null) {
                response.setNomeColaborador(colaborador.getUsuario().getNome());
            }
        }

        Habito habito = meta.getHabito();
        if (habito != null) {
            response.setIdHabito(habito.getId());
            // Usa o campo `nome` da entidade Habito para preencher o título na resposta.
            response.setTituloHabito(habito.getNome());
        }

        response.setSemanaInicio(meta.getSemanaInicio());
        response.setSemanaFim(meta.getSemanaFim());
        response.setQuantidadeMeta(meta.getQuantidadeMeta());
        response.setStatus(meta.getStatus());

        return response;
    }
}
