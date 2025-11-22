package com.global.revoo.web.controller;

import com.global.revoo.service.MetaSemanalService;
import com.global.revoo.web.dto.meta.MetaSemanalRequest;
import com.global.revoo.web.dto.meta.MetaSemanalResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metas-semanais")
@Tag(name = "Metas Semanais", description = "Gestão de metas semanais por colaborador/hábito")
public class MetaSemanalController {

    private final MetaSemanalService metaSemanalService;

    public MetaSemanalController(MetaSemanalService metaSemanalService) {
        this.metaSemanalService = metaSemanalService;
    }

    @GetMapping
    @Operation(summary = "Lista todas as metas semanais")
    public ResponseEntity<List<MetaSemanalResponse>> listarTodos() {
        return ResponseEntity.ok(metaSemanalService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca meta semanal por id")
    public ResponseEntity<MetaSemanalResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(metaSemanalService.buscarPorId(id));
    }

    @GetMapping("/colaborador/{idColaborador}")
    @Operation(summary = "Lista metas semanais de um colaborador")
    public ResponseEntity<List<MetaSemanalResponse>> listarPorColaborador(@PathVariable Long idColaborador) {
        return ResponseEntity.ok(metaSemanalService.listarPorColaborador(idColaborador));
    }

    @PostMapping
    @Operation(summary = "Cria uma meta semanal")
    public ResponseEntity<MetaSemanalResponse> criar(@RequestBody MetaSemanalRequest request) {
        MetaSemanalResponse response = metaSemanalService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma meta semanal")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        metaSemanalService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
