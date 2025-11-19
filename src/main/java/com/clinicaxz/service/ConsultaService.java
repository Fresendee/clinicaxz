package com.clinicaxz.service;

import com.clinicaxz.enums.EnumStatusConsulta;
import com.clinicaxz.model.Consulta;
import com.clinicaxz.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }

    public List<Consulta> listarPorMedico(Long medicoId) {
        return consultaRepository.findByMedicoId(medicoId);
    }

    public List<Consulta> listarPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }

    public List<Consulta> listarAgendaMedico(Long medicoId, LocalDateTime inicio, LocalDateTime fim) {
        return consultaRepository.findConsultasAgendadasMedico(medicoId, inicio, fim);
    }

    public List<Consulta> listarAgendaCompleta(LocalDateTime inicio, LocalDateTime fim) {
        return consultaRepository.findConsultasPorPeriodo(inicio, fim);
    }

    @Transactional
    public Consulta agendar(Consulta consulta) {
        // Validar conflito de horários
        List<Consulta> consultasExistentes = consultaRepository.findConsultasAgendadasMedico(
                consulta.getMedico().getId(),
                consulta.getDataHora().minusMinutes(30),
                consulta.getDataHora().plusMinutes(30)
        );
        
        if (!consultasExistentes.isEmpty()) {
            throw new RuntimeException("Conflito de horário: médico já possui consulta agendada neste horário");
        }
        
        consulta.setStatus(EnumStatusConsulta.AGENDADA);
        return consultaRepository.save(consulta);
    }

    @Transactional
    public Consulta remarcar(Long id, LocalDateTime novaDataHora) {
        Consulta consulta = buscarPorId(id);
        
        if (consulta.getStatus() != EnumStatusConsulta.AGENDADA) {
            throw new RuntimeException("Apenas consultas agendadas podem ser remarcadas");
        }
        
        // Validar conflito de horários
        List<Consulta> consultasExistentes = consultaRepository.findConsultasAgendadasMedico(
                consulta.getMedico().getId(),
                novaDataHora.minusMinutes(30),
                novaDataHora.plusMinutes(30)
        );
        
        if (!consultasExistentes.isEmpty() && !consultasExistentes.get(0).getId().equals(id)) {
            throw new RuntimeException("Conflito de horário: médico já possui consulta agendada neste horário");
        }
        
        consulta.setDataHora(novaDataHora);
        return consultaRepository.save(consulta);
    }

    @Transactional
    public void cancelar(Long id) {
        Consulta consulta = buscarPorId(id);
        
        if (consulta.getStatus() == EnumStatusConsulta.REALIZADA) {
            throw new RuntimeException("Não é possível cancelar uma consulta já realizada");
        }
        
        consulta.setStatus(EnumStatusConsulta.CANCELADA);
        consultaRepository.save(consulta);
    }

    @Transactional
    public void marcarComoRealizada(Long id) {
        Consulta consulta = buscarPorId(id);
        consulta.setStatus(EnumStatusConsulta.REALIZADA);
        consultaRepository.save(consulta);
    }
}
