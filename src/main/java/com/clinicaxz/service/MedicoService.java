package com.clinicaxz.service;

import com.clinicaxz.model.Medico;
import com.clinicaxz.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public List<Medico> listarAtivos() {
        return medicoRepository.findByAtivoTrue();
    }

    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
    }

    public List<Medico> buscarPorEspecialidade(Long especialidadeId) {
        return medicoRepository.findByEspecialidadeId(especialidadeId);
    }

    @Transactional
    public Medico criar(Medico medico) {
        return medicoRepository.save(medico);
    }

    @Transactional
    public Medico atualizar(Long id, Medico medicoAtualizado) {
        Medico medico = buscarPorId(id);
        medico.setNome(medicoAtualizado.getNome());
        medico.setCpf(medicoAtualizado.getCpf());
        medico.setTelefone(medicoAtualizado.getTelefone());
        medico.setEmail(medicoAtualizado.getEmail());
        medico.setEndereco(medicoAtualizado.getEndereco());
        medico.setCrm(medicoAtualizado.getCrm());
        medico.setEspecialidade(medicoAtualizado.getEspecialidade());
        return medicoRepository.save(medico);
    }

    @Transactional
    public void inativar(Long id) {
        Medico medico = buscarPorId(id);
        medico.setAtivo(false);
        medicoRepository.save(medico);
    }
}
