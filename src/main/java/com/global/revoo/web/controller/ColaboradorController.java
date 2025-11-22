package com.global.revoo.web.controller;

import com.global.revoo.domain.model.Colaborador;
import com.global.revoo.service.ColaboradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colaboradores")
@Tag(name = "Colaboradores", description = "CRUD de colaboradores")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping
    @Operation(summary = "Lista todos os colaboradores")
    public ResponseEntity<List<Colaborador>> listarTodos() {
        return ResponseEntity.ok(colaboradorService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca colaborador por id")
    public ResponseEntity<Colaborador> buscarPorId(@PathVariable Long id) {
        Colaborador colaborador = colaboradorService.buscarPorId(id);
        return ResponseEntity.ok(colaborador);
    }

    @PostMapping
    @Operation(summary = "Cria um colaborador")
    public ResponseEntity<Colaborador> criar(@RequestBody Colaborador colaborador) {
        Colaborador criado = colaboradorService.criar(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um colaborador")
    public ResponseEntity<Colaborador> atualizar(@PathVariable Long id,
                                                 @RequestBody Colaborador colaborador) {
        Colaborador atualizado = colaboradorService.atualizar(id, colaborador);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um colaborador")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        colaboradorService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
