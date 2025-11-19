package com.clinicaxz.service;

import com.clinicaxz.model.Paciente;
import com.clinicaxz.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public List<Paciente> listarAtivos() {
        return pacienteRepository.findByAtivoTrue();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    @Transactional
    public Paciente criar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente atualizar(Long id, Paciente pacienteAtualizado) {
        Paciente paciente = buscarPorId(id);
        paciente.setNome(pacienteAtualizado.getNome());
        paciente.setCpf(pacienteAtualizado.getCpf());
        paciente.setTelefone(pacienteAtualizado.getTelefone());
        paciente.setEmail(pacienteAtualizado.getEmail());
        paciente.setEndereco(pacienteAtualizado.getEndereco());
        paciente.setDataNascimento(pacienteAtualizado.getDataNascimento());
        paciente.setSexo(pacienteAtualizado.getSexo());
        paciente.setTipoSanguineo(pacienteAtualizado.getTipoSanguineo());
        paciente.setObservacoes(pacienteAtualizado.getObservacoes());
        return pacienteRepository.save(paciente);
    }

    @Transactional
    public void inativar(Long id) {
        Paciente paciente = buscarPorId(id);
        paciente.setAtivo(false);
        pacienteRepository.save(paciente);
    }
}
