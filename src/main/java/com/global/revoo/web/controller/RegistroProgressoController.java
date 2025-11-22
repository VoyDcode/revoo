package com.global.revoo.web.controller;

import com.global.revoo.service.RegistroProgressoService;
import com.global.revoo.web.dto.progresso.RegistroProgressoRequest;
import com.global.revoo.web.dto.progresso.RegistroProgressoResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros-progresso")
public class RegistroProgressoController {

    private final RegistroProgressoService registroProgressoService;

    public RegistroProgressoController(RegistroProgressoService registroProgressoService) {
        this.registroProgressoService = registroProgressoService;
    }

    @PostMapping
    public ResponseEntity<RegistroProgressoResponse> criar(
            @Valid @RequestBody RegistroProgressoRequest request) {
        return ResponseEntity.ok(registroProgressoService.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroProgressoResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody RegistroProgressoRequest request) {
        return ResponseEntity.ok(registroProgressoService.atualizar(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroProgressoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(registroProgressoService.buscarPorId(id));
    }

    @GetMapping("/meta/{idMeta}")
    public ResponseEntity<List<RegistroProgressoResponse>> listarPorMeta(
            @PathVariable Long idMeta) {
        return ResponseEntity.ok(registroProgressoService.listarPorMeta(idMeta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        registroProgressoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
