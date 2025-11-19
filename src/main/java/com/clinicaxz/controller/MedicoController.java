package com.clinicaxz.controller;

import com.clinicaxz.model.Medico;
import com.clinicaxz.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@Tag(name = "Médicos", description = "Gerenciamento de Médicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    @Operation(summary = "Listar todos os médicos")
    public ResponseEntity<List<Medico>> listarTodos() {
        return ResponseEntity.ok(medicoService.listarTodos());
    }

    @GetMapping("/ativos")
    @Operation(summary = "Listar médicos ativos")
    public ResponseEntity<List<Medico>> listarAtivos() {
        return ResponseEntity.ok(medicoService.listarAtivos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar médico por ID")
    public ResponseEntity<Medico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.buscarPorId(id));
    }

    @GetMapping("/especialidade/{especialidadeId}")
    @Operation(summary = "Buscar médicos por especialidade")
    public ResponseEntity<List<Medico>> buscarPorEspecialidade(@PathVariable Long especialidadeId) {
        return ResponseEntity.ok(medicoService.buscarPorEspecialidade(especialidadeId));
    }

    @PostMapping
    @Operation(summary = "Criar novo médico")
    public ResponseEntity<Medico> criar(@RequestBody Medico medico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.criar(medico));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar médico")
    public ResponseEntity<Medico> atualizar(@PathVariable Long id, @RequestBody Medico medico) {
        return ResponseEntity.ok(medicoService.atualizar(id, medico));
    }

    @PatchMapping("/{id}/inativar")
    @Operation(summary = "Inativar médico")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        medicoService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
