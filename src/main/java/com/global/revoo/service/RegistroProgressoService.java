package com.global.revoo.service;

import com.global.revoo.domain.model.MetaSemanal;
import com.global.revoo.domain.model.RegistroProgresso;
import com.global.revoo.domain.repository.MetaSemanalRepository;
import com.global.revoo.domain.repository.RegistroProgressoRepository;
import com.global.revoo.web.dto.progresso.RegistroProgressoRequest;
import com.global.revoo.web.dto.progresso.RegistroProgressoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistroProgressoService {

    private final RegistroProgressoRepository registroProgressoRepository;
    private final MetaSemanalRepository metaSemanalRepository;

    public RegistroProgressoService(RegistroProgressoRepository registroProgressoRepository,
                                    MetaSemanalRepository metaSemanalRepository) {
        this.registroProgressoRepository = registroProgressoRepository;
        this.metaSemanalRepository = metaSemanalRepository;
    }

    @Transactional
    public RegistroProgressoResponse criar(RegistroProgressoRequest dto) {
        MetaSemanal meta = metaSemanalRepository.findById(dto.getIdMetaSemanal())
                .orElseThrow(() -> new IllegalArgumentException("Meta semanal não encontrada"));

        RegistroProgresso reg = new RegistroProgresso();
        reg.setMetaSemanal(meta);
        reg.setDataRegistro(dto.getDataRegistro());
        reg.setQuantidadeProgresso(dto.getQuantidadeProgresso());
        reg.setObservacao(dto.getObservacao());

        RegistroProgresso salvo = registroProgressoRepository.save(reg);
        return toResponse(salvo);
    }

    @Transactional
    public RegistroProgressoResponse atualizar(Long idRegistro, RegistroProgressoRequest dto) {
        RegistroProgresso reg = registroProgressoRepository.findById(idRegistro)
                .orElseThrow(() -> new IllegalArgumentException("Registro de progresso não encontrado"));

        MetaSemanal meta = metaSemanalRepository.findById(dto.getIdMetaSemanal())
                .orElseThrow(() -> new IllegalArgumentException("Meta semanal não encontrada"));

        reg.setMetaSemanal(meta);
        reg.setDataRegistro(dto.getDataRegistro());
        reg.setQuantidadeProgresso(dto.getQuantidadeProgresso());
        reg.setObservacao(dto.getObservacao());

        RegistroProgresso atualizado = registroProgressoRepository.save(reg);
        return toResponse(atualizado);
    }

    @Transactional(readOnly = true)
    public RegistroProgressoResponse buscarPorId(Long idRegistro) {
        RegistroProgresso reg = registroProgressoRepository.findById(idRegistro)
                .orElseThrow(() -> new IllegalArgumentException("Registro de progresso não encontrado"));
        return toResponse(reg);
    }

    @Transactional(readOnly = true)
    public List<RegistroProgressoResponse> listarPorMeta(Long idMetaSemanal) {
        return registroProgressoRepository.findByMetaSemanalId(idMetaSemanal)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public void excluir(Long idRegistro) {
        if (!registroProgressoRepository.existsById(idRegistro)) {
            throw new IllegalArgumentException("Registro de progresso não encontrado");
        }
        registroProgressoRepository.deleteById(idRegistro);
    }

    private RegistroProgressoResponse toResponse(RegistroProgresso reg) {
        RegistroProgressoResponse resp = new RegistroProgressoResponse();
        resp.setId(reg.getId());
        resp.setIdMetaSemanal(reg.getMetaSemanal().getId());
        resp.setDataRegistro(reg.getDataRegistro());
        resp.setQuantidadeProgresso(reg.getQuantidadeProgresso());
        resp.setObservacao(reg.getObservacao());

        if (reg.getMetaSemanal() != null) {
            resp.setSemanaInicio(reg.getMetaSemanal().getSemanaInicio());
            resp.setSemanaFim(reg.getMetaSemanal().getSemanaFim());
        }

        return resp;
    }
}
