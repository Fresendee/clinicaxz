package com.clinicaxz.service;

import com.clinicaxz.model.Convenio;
import com.clinicaxz.repository.ConvenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConvenioService {

    @Autowired
    private ConvenioRepository convenioRepository;

    public List<Convenio> listarTodos() {
        return convenioRepository.findAll();
    }

    public List<Convenio> listarAtivos() {
        return convenioRepository.findByAtivoTrue();
    }

    public Convenio buscarPorId(Long id) {
        return convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convênio não encontrado"));
    }

    @Transactional
    public Convenio criar(Convenio convenio) {
        return convenioRepository.save(convenio);
    }

    @Transactional
    public Convenio atualizar(Long id, Convenio convenioAtualizado) {
        Convenio convenio = buscarPorId(id);
        convenio.setNome(convenioAtualizado.getNome());
        convenio.setCnpj(convenioAtualizado.getCnpj());
        convenio.setTelefone(convenioAtualizado.getTelefone());
        convenio.setEmail(convenioAtualizado.getEmail());
        return convenioRepository.save(convenio);
    }

    @Transactional
    public void inativar(Long id) {
        Convenio convenio = buscarPorId(id);
        convenio.setAtivo(false);
        convenioRepository.save(convenio);
    }
}
