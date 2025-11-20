package com.global.revoo.service;

import com.global.revoo.domain.model.Colaborador;
import com.global.revoo.domain.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {

    private final ColaboradorRepository repository;

    public ColaboradorService(ColaboradorRepository repository) {
        this.repository = repository;
    }

    public List<Colaborador> listarTodos() {
        return repository.findAll();
    }

    public Colaborador buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));
    }

    public Colaborador criar(Colaborador colaborador) {
        if (repository.existsByEmail(colaborador.getEmail())) {
            throw new RuntimeException("Já existe um colaborador com esse email");
        }
        return repository.save(colaborador);
    }

    public Colaborador atualizar(Long id, Colaborador dados) {
        Colaborador existente = buscarPorId(id);

        existente.setNome(dados.getNome());
        existente.setEmail(dados.getEmail());
        existente.setDepartamento(dados.getDepartamento());

        return repository.save(existente);
    }

    public void excluir(Long id) {
        Colaborador existente = buscarPorId(id);
        repository.delete(existente);
    }
}
