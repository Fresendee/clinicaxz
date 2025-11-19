package com.clinicaxz.service;

import com.clinicaxz.model.Especialidade;
import com.clinicaxz.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public List<Especialidade> listarTodas() {
        return especialidadeRepository.findAll();
    }

    public List<Especialidade> listarAtivas() {
        return especialidadeRepository.findByAtivoTrue();
    }

    public Especialidade buscarPorId(Long id) {
        return especialidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));
    }

    @Transactional
    public Especialidade criar(Especialidade especialidade) {
        return especialidadeRepository.save(especialidade);
    }

    @Transactional
    public Especialidade atualizar(Long id, Especialidade especialidadeAtualizada) {
        Especialidade especialidade = buscarPorId(id);
        especialidade.setNome(especialidadeAtualizada.getNome());
        especialidade.setDescricao(especialidadeAtualizada.getDescricao());
        return especialidadeRepository.save(especialidade);
    }

    @Transactional
    public void inativar(Long id) {
        Especialidade especialidade = buscarPorId(id);
        especialidade.setAtivo(false);
        especialidadeRepository.save(especialidade);
    }

    @Transactional
    public void deletar(Long id) {
        especialidadeRepository.deleteById(id);
    }
}
