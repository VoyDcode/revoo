package com.global.revoo.service;

import com.global.revoo.domain.model.Colaborador;
import com.global.revoo.domain.model.Usuario;
import com.global.revoo.domain.repository.ColaboradorRepository;
import com.global.revoo.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final UsuarioRepository usuarioRepository;

    public ColaboradorService(ColaboradorRepository colaboradorRepository,
                              UsuarioRepository usuarioRepository) {
        this.colaboradorRepository = colaboradorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Colaborador> listarTodos() {
        return colaboradorRepository.findAll();
    }

    public Colaborador buscarPorId(Long id) {
        return colaboradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));
    }

    public Colaborador criar(Colaborador colaborador) {
        if (colaborador.getUsuario() == null || colaborador.getUsuario().getId() == null) {
            throw new RuntimeException("Usuário do colaborador é obrigatório");
        }

        Long idUsuario = colaborador.getUsuario().getId();

        if (colaboradorRepository.existsByUsuarioId(idUsuario)) {
            throw new RuntimeException("Já existe colaborador vinculado a esse usuário");
        }

        if (colaborador.getMatricula() != null &&
                colaboradorRepository.existsByMatricula(colaborador.getMatricula())) {
            throw new RuntimeException("Já existe colaborador com essa matrícula");
        }

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        colaborador.setUsuario(usuario);

        return colaboradorRepository.save(colaborador);
    }

    public Colaborador atualizar(Long id, Colaborador dados) {
        Colaborador existente = buscarPorId(id);

        if (dados.getUsuario() != null && dados.getUsuario().getId() != null &&
                !dados.getUsuario().getId().equals(existente.getUsuario().getId())) {

            Long novoUsuarioId = dados.getUsuario().getId();

            if (colaboradorRepository.existsByUsuarioId(novoUsuarioId)) {
                throw new RuntimeException("Já existe colaborador vinculado ao novo usuário informado");
            }

            Usuario novoUsuario = usuarioRepository.findById(novoUsuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            existente.setUsuario(novoUsuario);
        }

        if (dados.getMatricula() != null &&
                !dados.getMatricula().equals(existente.getMatricula())) {

            if (colaboradorRepository.existsByMatricula(dados.getMatricula())) {
                throw new RuntimeException("Já existe colaborador com essa matrícula");
            }
            existente.setMatricula(dados.getMatricula());
        }

        if (dados.getCargo() != null) {
            existente.setCargo(dados.getCargo());
        }

        if (dados.getSetor() != null) {
            existente.setSetor(dados.getSetor());
        }

        if (dados.getDataAdmissao() != null) {
            existente.setDataAdmissao(dados.getDataAdmissao());
        }

        if (dados.getDataDesligamento() != null) {
            existente.setDataDesligamento(dados.getDataDesligamento());
        }

        return colaboradorRepository.save(existente);
    }

    public void remover(Long id) {
        Colaborador existente = buscarPorId(id);
        colaboradorRepository.delete(existente);
    }
}
