package com.clinicaxz.service;

import com.clinicaxz.model.Prontuario;
import com.clinicaxz.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private ConsultaService consultaService;

    public Prontuario buscarPorId(Long id) {
        return prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
    }

    public Prontuario buscarPorConsulta(Long consultaId) {
        return prontuarioRepository.findByConsultaId(consultaId)
                .orElse(null);
    }

    public List<Prontuario> buscarHistoricoPaciente(Long pacienteId) {
        return prontuarioRepository.findHistoricoPaciente(pacienteId);
    }

    @Transactional
    public Prontuario registrar(Prontuario prontuario) {
        prontuario.setDataRegistro(LocalDateTime.now());
        Prontuario prontuarioSalvo = prontuarioRepository.save(prontuario);
        
        // Marcar consulta como realizada
        consultaService.marcarComoRealizada(prontuario.getConsulta().getId());
        
        return prontuarioSalvo;
    }

    @Transactional
    public Prontuario atualizar(Long id, Prontuario prontuarioAtualizado) {
        Prontuario prontuario = buscarPorId(id);
        prontuario.setQueixaPrincipal(prontuarioAtualizado.getQueixaPrincipal());
        prontuario.setHistoriaDoenca(prontuarioAtualizado.getHistoriaDoenca());
        prontuario.setExameFisico(prontuarioAtualizado.getExameFisico());
        prontuario.setHipoteseDiagnostica(prontuarioAtualizado.getHipoteseDiagnostica());
        prontuario.setConduta(prontuarioAtualizado.getConduta());
        prontuario.setPrescricao(prontuarioAtualizado.getPrescricao());
        prontuario.setObservacoes(prontuarioAtualizado.getObservacoes());
        return prontuarioRepository.save(prontuario);
    }
}
