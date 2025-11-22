package com.global.revoo.web.controller;

import com.global.revoo.domain.model.CategoriaHabito;
import com.global.revoo.service.HabitoService;
import com.global.revoo.web.dto.habito.HabitoRequest;
import com.global.revoo.web.dto.habito.HabitoResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habitos")
public class HabitoController {

    private final HabitoService habitoService;

    public HabitoController(HabitoService habitoService) {
        this.habitoService = habitoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HabitoResponse criar(@Valid @RequestBody HabitoRequest request) {
        return habitoService.criar(request);
    }

    @GetMapping("/{id}")
    public HabitoResponse buscarPorId(@PathVariable Long id) {
        return habitoService.buscarPorId(id);
    }

    @GetMapping
    public Page<HabitoResponse> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) CategoriaHabito categoria,
            @RequestParam(required = false) Boolean ativo
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return habitoService.listar(titulo, categoria, ativo, pageable);
    }

    @PutMapping("/{id}")
    public HabitoResponse atualizar(@PathVariable Long id,
                                    @Valid @RequestBody HabitoRequest request) {
        return habitoService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        habitoService.excluir(id);
    }
}
