package com.clinicaxz.repository;

import com.clinicaxz.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
    List<Especialidade> findByAtivoTrue();
}
