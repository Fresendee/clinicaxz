package com.clinicaxz.repository;

import com.clinicaxz.enums.EnumStatusUsuario;
import com.clinicaxz.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByUsernameAndSenha(String username, String senha);
    List<Usuario> findByStatus(EnumStatusUsuario status);
}
