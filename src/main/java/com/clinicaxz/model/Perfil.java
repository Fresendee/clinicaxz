package com.clinicaxz.model;

import com.clinicaxz.enums.EnumFuncionalidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "perfis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @ElementCollection(targetClass = EnumFuncionalidades.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "perfil_funcionalidades", joinColumns = @JoinColumn(name = "perfil_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "funcionalidade")
    private Set<EnumFuncionalidades> funcionalidades = new HashSet<>();
}
