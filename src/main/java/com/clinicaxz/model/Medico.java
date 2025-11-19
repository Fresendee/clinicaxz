package com.clinicaxz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicos")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends Pessoa {

    @Column(nullable = false, unique = true, length = 20)
    private String crm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "especialidade_id", nullable = false)
    private Especialidade especialidade;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false)
    private Boolean ativo = true;
}
