package com.clinicaxz.controller;

import com.clinicaxz.model.Consulta;
import com.clinicaxz.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/consultas")
@Tag(name = "Consultas", description = "Gerenciamento de Consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    @Operation(summary = "Listar todas as consultas")
    public ResponseEntity<List<Consulta>> listarTodas() {
        return ResponseEntity.ok(consultaService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar consulta por ID")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }

    @GetMapping("/medico/{medicoId}")
    @Operation(summary = "Listar consultas por médico")
    public ResponseEntity<List<Consulta>> listarPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(consultaService.listarPorMedico(medicoId));
    }

    @GetMapping("/paciente/{pacienteId}")
    @Operation(summary = "Listar consultas por paciente")
    public ResponseEntity<List<Consulta>> listarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(consultaService.listarPorPaciente(pacienteId));
    }

    @GetMapping("/agenda/medico/{medicoId}")
    @Operation(summary = "Visualizar agenda do médico")
    public ResponseEntity<List<Consulta>> visualizarAgendaMedico(
            @PathVariable Long medicoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(consultaService.listarAgendaMedico(medicoId, inicio, fim));
    }

    @GetMapping("/agenda/completa")
    @Operation(summary = "Visualizar agenda completa da clínica")
    public ResponseEntity<List<Consulta>> visualizarAgendaCompleta(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(consultaService.listarAgendaCompleta(inicio, fim));
    }

    @PostMapping
    @Operation(summary = "Agendar nova consulta")
    public ResponseEntity<Consulta> agendar(@RequestBody Consulta consulta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.agendar(consulta));
    }

    @PatchMapping("/{id}/remarcar")
    @Operation(summary = "Remarcar consulta")
    public ResponseEntity<Consulta> remarcar(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        LocalDateTime novaDataHora = LocalDateTime.parse(body.get("novaDataHora"));
        return ResponseEntity.ok(consultaService.remarcar(id, novaDataHora));
    }

    @PatchMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar consulta")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        consultaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/realizar")
    @Operation(summary = "Marcar consulta como realizada")
    public ResponseEntity<Void> marcarComoRealizada(@PathVariable Long id) {
        consultaService.marcarComoRealizada(id);
        return ResponseEntity.noContent().build();
    }
}
