package com.clinicaxz.controller;

import com.clinicaxz.model.Convenio;
import com.clinicaxz.service.ConvenioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/convenios")
@Tag(name = "Convênios", description = "Gerenciamento de Convênios")
public class ConvenioController {

    @Autowired
    private ConvenioService convenioService;

    @GetMapping
    @Operation(summary = "Listar todos os convênios")
    public ResponseEntity<List<Convenio>> listarTodos() {
        return ResponseEntity.ok(convenioService.listarTodos());
    }

    @GetMapping("/ativos")
    @Operation(summary = "Listar convênios ativos")
    public ResponseEntity<List<Convenio>> listarAtivos() {
        return ResponseEntity.ok(convenioService.listarAtivos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar convênio por ID")
    public ResponseEntity<Convenio> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(convenioService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar novo convênio")
    public ResponseEntity<Convenio> criar(@RequestBody Convenio convenio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(convenioService.criar(convenio));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar convênio")
    public ResponseEntity<Convenio> atualizar(@PathVariable Long id, @RequestBody Convenio convenio) {
        return ResponseEntity.ok(convenioService.atualizar(id, convenio));
    }

    @PatchMapping("/{id}/inativar")
    @Operation(summary = "Inativar convênio")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        convenioService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
