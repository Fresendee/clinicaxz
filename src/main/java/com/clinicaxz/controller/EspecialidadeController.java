package com.clinicaxz.controller;

import com.clinicaxz.model.Especialidade;
import com.clinicaxz.service.EspecialidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
@Tag(name = "Especialidades", description = "Gerenciamento de Especialidades Médicas")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping
    @Operation(summary = "Listar todas as especialidades")
    public ResponseEntity<List<Especialidade>> listarTodas() {
        return ResponseEntity.ok(especialidadeService.listarTodas());
    }

    @GetMapping("/ativas")
    @Operation(summary = "Listar especialidades ativas")
    public ResponseEntity<List<Especialidade>> listarAtivas() {
        return ResponseEntity.ok(especialidadeService.listarAtivas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar especialidade por ID")
    public ResponseEntity<Especialidade> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(especialidadeService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar nova especialidade")
    public ResponseEntity<Especialidade> criar(@RequestBody Especialidade especialidade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(especialidadeService.criar(especialidade));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar especialidade")
    public ResponseEntity<Especialidade> atualizar(@PathVariable Long id, @RequestBody Especialidade especialidade) {
        return ResponseEntity.ok(especialidadeService.atualizar(id, especialidade));
    }

    @PatchMapping("/{id}/inativar")
    @Operation(summary = "Inativar especialidade")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        especialidadeService.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar especialidade")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        especialidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
