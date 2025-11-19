package com.clinicaxz.repository;

import com.clinicaxz.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByCrm(String crm);
    List<Medico> findByAtivoTrue();
    List<Medico> findByEspecialidadeId(Long especialidadeId);
}
