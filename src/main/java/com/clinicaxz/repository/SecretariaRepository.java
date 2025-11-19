package com.clinicaxz.repository;

import com.clinicaxz.model.Secretaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecretariaRepository extends JpaRepository<Secretaria, Long> {
    Optional<Secretaria> findByPis(String pis);
    List<Secretaria> findByAtivoTrue();
}
