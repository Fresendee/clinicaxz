package com.clinicaxz.repository;

import com.clinicaxz.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    Optional<Prontuario> findByConsultaId(Long consultaId);
    
    @Query("SELECT p FROM Prontuario p JOIN p.consulta c WHERE c.paciente.id = :pacienteId ORDER BY p.dataRegistro DESC")
    List<Prontuario> findHistoricoPaciente(@Param("pacienteId") Long pacienteId);
}
