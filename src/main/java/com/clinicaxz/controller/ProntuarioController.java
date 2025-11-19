package com.clinicaxz.controller;

import com.clinicaxz.model.Prontuario;
import com.clinicaxz.service.ProntuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prontuarios")
@Tag(name = "Prontuários", description = "Gerenciamento de Prontuários Médicos")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar prontuário por ID")
    public ResponseEntity<Prontuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prontuarioService.buscarPorId(id));
    }

    @GetMapping("/consulta/{consultaId}")
    @Operation(summary = "Buscar prontuário por consulta")
    public ResponseEntity<Prontuario> buscarPorConsulta(@PathVariable Long consultaId) {
        Prontuario prontuario = prontuarioService.buscarPorConsulta(consultaId);
        if (prontuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prontuario);
    }

    @GetMapping("/historico/paciente/{pacienteId}")
    @Operation(summary = "Buscar histórico clínico completo do paciente")
    public ResponseEntity<List<Prontuario>> buscarHistoricoPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(prontuarioService.buscarHistoricoPaciente(pacienteId));
    }

    @PostMapping
    @Operation(summary = "Registrar novo prontuário")
    public ResponseEntity<Prontuario> registrar(@RequestBody Prontuario prontuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prontuarioService.registrar(prontuario));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar prontuário")
    public ResponseEntity<Prontuario> atualizar(@PathVariable Long id, @RequestBody Prontuario prontuario) {
        return ResponseEntity.ok(prontuarioService.atualizar(id, prontuario));
    }
}
