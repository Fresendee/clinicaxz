package com.clinicaxz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends Pessoa {

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(length = 50)
    private String sexo;

    @Column(name = "tipo_sanguineo", length = 5)
    private String tipoSanguineo;

    @Column(length = 1000)
    private String observacoes;

    @Column(nullable = false)
    private Boolean ativo = true;
}
