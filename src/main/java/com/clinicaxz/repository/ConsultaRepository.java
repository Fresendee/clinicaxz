package com.clinicaxz.repository;

import com.clinicaxz.enums.EnumStatusConsulta;
import com.clinicaxz.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByMedicoId(Long medicoId);
    List<Consulta> findByPacienteId(Long pacienteId);
    List<Consulta> findByStatus(EnumStatusConsulta status);
    
    @Query("SELECT c FROM Consulta c WHERE c.medico.id = :medicoId AND c.dataHora BETWEEN :inicio AND :fim AND c.status = 'AGENDADA'")
    List<Consulta> findConsultasAgendadasMedico(@Param("medicoId") Long medicoId, 
                                                  @Param("inicio") LocalDateTime inicio, 
                                                  @Param("fim") LocalDateTime fim);
    
    @Query("SELECT c FROM Consulta c WHERE c.dataHora BETWEEN :inicio AND :fim")
    List<Consulta> findConsultasPorPeriodo(@Param("inicio") LocalDateTime inicio, 
                                            @Param("fim") LocalDateTime fim);
}
