package com.clinicaxz.model;

import com.clinicaxz.enums.EnumStatusConsulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "convenio_id")
    private Convenio convenio;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "is_retorno", nullable = false)
    private Boolean isRetorno = false;

    @Column(name = "carteira_convenio", length = 50)
    private String carteiraConvenio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumStatusConsulta status = EnumStatusConsulta.AGENDADA;

    @Column(length = 1000)
    private String observacoes;

    @OneToOne(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Prontuario prontuario;
}
